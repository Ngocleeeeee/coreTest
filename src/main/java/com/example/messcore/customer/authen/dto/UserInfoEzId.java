package com.example.messcore.customer.authen.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ezcloud.message.booking.CustomerType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoEzId {
    public String sub;
    public String name;
    public String address;
    public LocalDateTime birthdate;
    public String website;
    public String role;
    public String preferred_username;
    public String email;
    public boolean email_verified;
    public CustomerType type;
    public UUID propertyId;
}