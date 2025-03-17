package com.example.messcore.messsage.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class MessageUpdateRequest {
    private List<UUID> messageIds;
    private boolean isRead;
}
