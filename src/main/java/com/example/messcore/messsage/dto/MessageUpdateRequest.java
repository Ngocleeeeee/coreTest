package com.example.messcore.messsage.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Data
public class MessageUpdateRequest {
    private List<UUID> messageIds;
    private boolean isRead;
}
