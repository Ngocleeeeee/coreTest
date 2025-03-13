package com.example.messcore.customer.authen.controller;


import com.example.messcore.common.dto.Res;
import com.example.messcore.customer.authen.dto.CustomerRequest;
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
    public Res loginCustomerOrGuest(@RequestBody CustomerRequest userInfo) {

        String token = authService.authenticateCustomerOrGuest(userInfo);
        return new Res(Res.STATUS_OK, token);
    }

    @PostMapping("/staff/login")
    public Res loginStaff(@RequestBody UserInfoEzId userInfo) {
        String token = authService.authenticateStaff(userInfo);
        return new Res(Res.STATUS_OK, token);
    }

}
