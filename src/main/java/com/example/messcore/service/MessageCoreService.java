package com.example.messcore.service;

import com.example.messcore.dto.MessageWrapper;
import com.example.messcore.entity.Conversation;
import com.example.messcore.entity.Message;
import com.example.messcore.repository.ConversationRepository;
import com.example.messcore.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageCoreService {
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final RabbitTemplate rabbitTemplate;

    @Transactional
    public void processMessage(MessageWrapper.MessageData messageDTO, String queueName) {
        UUID customerId = messageDTO.getRelationships().getCustomer().getData().getId();
        UUID staffId = messageDTO.getRelationships().getStaff().getData().getId();

        // Kiểm tra hội thoại có tồn tại chưa
        Conversation conversation = conversationRepository.findByCustomerId(customerId)
                .orElseGet(() -> {
                    Conversation newConversation = new Conversation();
                    newConversation.setActive((byte) 1);
                    newConversation.setPropertyId(customerId);
                    newConversation.setPropertyType("HOTEL");
                    newConversation.setCustomerId(customerId);
                    newConversation.setIsClose(false);
                    return conversationRepository.save(newConversation);
                });


        // Lưu tin nhắn vào database
        Message message = new Message();
        message.setConversationId(conversation.getId());
        message.setContent(messageDTO.getAttributes().getContent());
        message.setExternalMessageCode(messageDTO.getAttributes().getExternalMessageCode());
        message.setFromAi(messageDTO.getAttributes().getFromAi());
        message.setContentType(message.getContentType());
        message.setIsProperty(messageDTO.getAttributes().getIsProperty());
        message.setIsRead(messageDTO.getAttributes().getIsRead());
        message.setUpdatedDate(messageDTO.getAttributes().getUpdatedDate());
        message.setCustomerId(customerId);
        message.setStaffId(staffId);
        messageRepository.save(message);

        rabbitTemplate.convertAndSend(queueName, messageDTO);
    }
}
