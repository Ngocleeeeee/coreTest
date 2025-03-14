package com.example.messcore.messsage.controller;

import com.example.messcore.hotel.dto.HotelDto;
import com.example.messcore.messsage.dto.MessageUpdateRequest;
import com.example.messcore.messsage.service.MessageCoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
@Slf4j
public class MessageReadController {
    private final MessageCoreService messageCoreService;

    @PutMapping("/updateStatus")
    public ResponseEntity<String> updateMessageReadStatus(@RequestBody MessageUpdateRequest messageUpdateRequest) {

        messageCoreService.updateMessageReadStatus(messageUpdateRequest.getMessageId(), messageUpdateRequest.isRead());
        return ResponseEntity.ok("Cập nhật trạng thái đọc thành công");
    }
}
