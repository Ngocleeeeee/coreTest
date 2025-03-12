package com.example.messcore.customer.authen.controller;


import com.example.messcore.common.dto.Res;
import com.example.messcore.customer.authen.dto.UserInfoEzId;
import com.example.messcore.customer.authen.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public Res login(@RequestBody UserInfoEzId userInfo) {
        String token = authService.authenticate(userInfo);
        return new Res(Res.STATUS_OK, token);
    }

}
