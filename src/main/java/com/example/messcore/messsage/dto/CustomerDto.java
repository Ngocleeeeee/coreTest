package com.example.messcore.messsage.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link ezcloud.message.booking.Customer}
 */
@Getter
@Setter
@JsonInclude
public class CustomerDto implements Serializable {
    String firstName;
    String lastName;
    String title;
    String gender;
    String email;
    String phone;
    String country;
    LocalDateTime birthdate;
    String identity;
    String companyName;
}