package com.example.messcore.messsage.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Data
public class MessageUpdateRequest {
    private UUID messageId;
    private boolean isRead;
}
