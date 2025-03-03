package com.example.messcore.authen.controller;


import com.example.messcore.authen.dto.GuestRequest;
import com.example.messcore.authen.service.AuthService;
import com.example.messcore.authen.service.EmailService;
import com.example.messcore.authen.service.OtpService;
import com.example.messcore.authen.service.RecaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.messcore.dto.RateLimitLogin;
import com.example.messcore.dto.Res;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import com.example.messcore.authen.dto.CustomerRequest;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OtpService otpService;

    @Autowired
    private RecaptchaService recaptchaService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/guest")
//    @RateLimitLogin(maxAttempts = 3, attemptWindow = 5, lockTime = 10)
    public Res loginAsGuest(@RequestBody GuestRequest requestBody) {
//        String recaptchaToken = requestBody.get("recaptchaToken");

        if (!recaptchaService.validateRecaptcha(requestBody.getRecaptchaToken())) {
            return new Res(Res.STATUS_ERR, "Invalid recaptcha token");
        }
        String token = authService.loginAsGuest(requestBody.getEmail());
        return new Res(Res.STATUS_OK, token);
    }

    @PostMapping("/login")
    public Res loginWithEmail(@RequestBody CustomerRequest request) {

        if (!recaptchaService.validateRecaptcha(request.getRecaptchaToken())) {
            return new Res(Res.STATUS_ERR, "Invalid recaptcha token");
        }
        String token = authService.loginWithEmail(request);
        return new Res(Res.STATUS_OK, "token: " + token);
    }

    @PostMapping("/request-otp")
    public Res requestOtp(@RequestBody String email) {
        String otp = otpService.generateOtp(email);
        emailService.sendEmail(email, "OTP: ", otp);
        return new Res(Res.STATUS_OK, "OTP sent to " + email);
    }
}
