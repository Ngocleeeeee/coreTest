package com.example.messcore.listener;

//import com.example.messcore.dto.MessageDTO;
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
        MessageWrapper.MessageData messageData = messageWrapper.getData();
        messageService.processMessage(messageData, "queueExOut");
    }

    @RabbitListener(queues = "queueExIn")
    public void receiveQueueExIn(MessageWrapper messageWrapper) {
        MessageWrapper.MessageData messageData = messageWrapper.getData();
        messageService.processMessage(messageData, "queueGWOut");
    }

}
