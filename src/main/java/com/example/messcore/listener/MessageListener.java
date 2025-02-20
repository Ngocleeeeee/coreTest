package com.example.messcore.listener;

import com.example.messcore.dto.MessageDTO;
import com.example.messcore.dto.MessageWrapper;
import com.example.messcore.service.MessageCoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageListener {
    private final MessageCoreService messageService;

    @RabbitListener(queues = "queueGWIn")
    public void receiveQueueGWIn(MessageWrapper messageWrapper) {
        MessageDTO messageDTO = messageWrapper.getData();
        messageService.processMessage(messageDTO, "queueExOut");
    }

    @RabbitListener(queues = "queueExIn")
    public void receiveQueueExIn(MessageWrapper messageWrapper) {
        MessageDTO messageDTO = messageWrapper.getData();
        messageService.processMessage(messageDTO, "queueGWOut");
    }

}
