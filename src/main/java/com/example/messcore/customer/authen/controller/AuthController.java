package com.example.messcore.customer.authen.controller;


import com.example.messcore.common.dto.Res;
import com.example.messcore.customer.authen.dto.CustomerRequest;
import com.example.messcore.customer.authen.dto.GuestRequest;
import com.example.messcore.customer.authen.dto.UserInfoEzId;
import com.example.messcore.customer.authen.service.AuthService;
import com.example.messcore.customer.authen.service.EmailService;
import com.example.messcore.customer.authen.service.OtpService;
import com.example.messcore.customer.authen.service.RecaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        String token = authService.loginAsGuest(requestBody.getEmail());
        return new Res(Res.STATUS_OK, token);
    }

    @PostMapping("/login")
    public Res loginWithEmail(@RequestBody CustomerRequest request) {

        String token = authService.loginWithEmail(request);
        return new Res(Res.STATUS_OK, "token: " + token);
    }


    @PostMapping("/staff/login")
    public Res login(@RequestBody UserInfoEzId userInfo) {
        String token = authService.authenticateStaff(userInfo);
        return new Res(Res.STATUS_OK, token);
    }
}
