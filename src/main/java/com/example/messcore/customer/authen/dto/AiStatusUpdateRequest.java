package com.example.messcore.customer.authen.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AiStatusUpdateRequest {
    private List<UUID> hotelIds;
    private boolean isAutoReply;
}
