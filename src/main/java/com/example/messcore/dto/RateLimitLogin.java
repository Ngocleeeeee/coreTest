package com.example.messcore.dto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimitLogin {
    int maxAttempts() default 3; // Số lần đăng nhập tối đa
    int attemptWindow() default 5; // Cửa sổ thời gian (phút)
    long lockTime() default 10;  // Thời gian khóa (phút)
}
