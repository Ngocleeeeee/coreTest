package com.example.messcore.rabbitmq.config;

import com.example.messcore.messsage.dto.MessageWrapper;
import com.example.messcore.messsage.service.MessageCoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor

public class ExtranetMessageHandler {
    private final MessageCoreService messageService;

    public void handleMessage(byte[] messageBytes) throws JsonProcessingException {
        String messageJson = new String(messageBytes, StandardCharsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        MessageWrapper messageWrapper = objectMapper.readValue(messageJson, MessageWrapper.class);
        MessageWrapper.MessageData messageData = messageWrapper.getData();
        try {
            String queueEnd ="hotel_gateway_out_"+messageData.getRelationships().getProperty().getId();
            messageService.processMessage(messageData, queueEnd);
            System.out.println("Received message for hotel " + messageData.getRelationships().getProperty().getId() + ": " + messageData.getAttributes().getContent());
        } catch (Exception e) {
            System.err.println("Error parsing message: " + messageData.getAttributes().getContent());
            e.printStackTrace();
        }
    }

}
