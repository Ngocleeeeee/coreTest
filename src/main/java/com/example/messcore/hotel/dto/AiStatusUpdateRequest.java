package com.example.messcore.hotel.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AiStatusUpdateRequest {

    private UUID hotelId;
    private boolean isAutoReply;
}
