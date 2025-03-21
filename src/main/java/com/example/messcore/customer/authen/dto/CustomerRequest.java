package com.example.messcore.customer.authen.dto;

import ezcloud.message.booking.CustomerType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CustomerRequest {

    @Email(message = "Email không hợp lệ")
    @NotBlank(message = "Email không được để trống")
    private String email;

    @NotBlank(message = "Họ tên không được để trống")
    private String name;

    public @Email(message = "Email không hợp lệ") @NotBlank(message = "Email không được để trống") String getEmail() {
        return email;
    }
    public CustomerType type;
    public UUID propertyId;

    public @NotBlank(message = "Họ tên không được để trống") String getName() {
        return name;
    }
}
