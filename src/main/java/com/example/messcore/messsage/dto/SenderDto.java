package com.example.messcore.messsage.dto;


import com.example.messcore.hotel.dto.HotelI18nDto;
import ezcloud.message.booking.CustomerType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class SenderDto implements Serializable {

    private UUID id;
    private CustomerType senderType;
    private String email;


}