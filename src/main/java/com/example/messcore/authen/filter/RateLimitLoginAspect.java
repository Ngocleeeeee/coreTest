package com.example.messcore.authen.filter;


import com.example.messcore.authen.utils.IPUtils;
import com.example.messcore.dto.RateLimitLogin;
import com.example.messcore.dto.Res;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Aspect
@Component
@Order(1)
public class RateLimitLoginAspect {
    private final StringRedisTemplate redisTemplate;
    private final HttpServletRequest request;

    public RateLimitLoginAspect(StringRedisTemplate redisTemplate, HttpServletRequest request) {
        this.redisTemplate = redisTemplate;
        this.request = request;
    }

    @Around("@annotation(rateLimitLogin)")
    public Object limitGuestLogin(ProceedingJoinPoint joinPoint, RateLimitLogin rateLimitLogin) throws Throwable {
        String ip = IPUtils.getClientIP(request);
        String attemptKey = "login_attempts:ip:" + ip;
        String blockKey = "blocked_ip:" + ip;

        int maxAttempts = rateLimitLogin.maxAttempts();  // 3 lần
        long attemptWindow = rateLimitLogin.attemptWindow(); // 5 phút
        long blockDuration = rateLimitLogin.lockTime(); // 10 phút

        // Kiểm tra nếu IP đang bị chặn
        if (Boolean.TRUE.equals(redisTemplate.hasKey(blockKey))) {
            Long remainingBlockTime = redisTemplate.getExpire(blockKey);
            return new Res(Res.STATUS_ERR, "Too many login attempts. Try again in "
                    + (remainingBlockTime != null ? remainingBlockTime : "unknown") + " seconds.");
        }

        // Kiểm tra số lần đăng nhập trong cửa sổ thời gian
        String attempts = redisTemplate.opsForValue().get(attemptKey);
        int currentAttempts = attempts != null ? Integer.parseInt(attempts) : 0;

        if (currentAttempts >= maxAttempts) {
            // Chặn IP trong blockDuration (10 phút)
            redisTemplate.opsForValue().set(blockKey, "blocked", Duration.ofMinutes(blockDuration));
            redisTemplate.delete(attemptKey); // Reset lại số lần thử để tránh tăng bộ đếm khi bị chặn
            return new Res(Res.STATUS_ERR, "Too many login attempts. You are blocked for " + blockDuration + " minutes.");
        }

        // Ghi nhận lần đăng nhập
        Long attemptCount = redisTemplate.opsForValue().increment(attemptKey);
        if (attemptCount == 1) {
            redisTemplate.expire(attemptKey, Duration.ofMinutes(attemptWindow)); // TTL 5 phút
        }

        // Cho phép login
        return joinPoint.proceed();
    }
}
