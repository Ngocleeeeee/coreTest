package com.example.messcore.customer.authen.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GuestRequest {
    private String email;
    private UUID propertyId;
}
