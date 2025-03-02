package com.example.messcore.authen.dto;

import ezcloud.message.booking.CustomerType;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuestCustomerNotification implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String email;
    private String firstname;
    private String lastname;
    private CustomerType customerType;
    private String type;
}
