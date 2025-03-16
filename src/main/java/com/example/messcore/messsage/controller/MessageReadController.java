package com.example.messcore.messsage.controller;

import com.example.messcore.common.dto.Res;
import com.example.messcore.messsage.dto.MessageUpdateRequest;
import com.example.messcore.messsage.service.MessageCoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
@Slf4j
public class MessageReadController {
    private final MessageCoreService messageCoreService;

    @PutMapping("/updateStatus")
    public Res updateMessagesReadStatus(@RequestBody MessageUpdateRequest messageUpdateRequest) {
        messageCoreService.updateMessagesReadStatus(messageUpdateRequest.getMessageIds(), messageUpdateRequest.isRead());
        return new Res(Res.STATUS_OK, messageUpdateRequest);
    }

}
