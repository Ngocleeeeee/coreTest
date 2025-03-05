package com.example.messcore.customer.authen.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestRequest {
    private String recaptchaToken;
    private String email;
}
