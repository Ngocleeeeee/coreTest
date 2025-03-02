package com.example.messcore.authen.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {
    private final StringRedisTemplate redisTemplate;
    private final Random random = new Random();

    public OtpService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String generateOtp(String email) {
        String otp = String.valueOf(100000 + random.nextInt(900000));
        redisTemplate.opsForValue().set("OTP_" + email, otp, 5, TimeUnit.MINUTES);
        return otp;
    }

    public boolean validateOtp(String email, String otp) {
        String storedOtp = redisTemplate.opsForValue().get("OTP_" + email);
        return storedOtp != null && storedOtp.equals(otp);
    }

    public void removeOtp(String email) {
        redisTemplate.delete("OTP_" + email);
    }
}
