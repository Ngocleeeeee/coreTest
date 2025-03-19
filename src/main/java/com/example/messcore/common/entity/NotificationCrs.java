package com.example.messcore.common.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationCrs {

    private String hotelCode;
    private Message content;
    private Sender sender;
    private Booking booking;
}
