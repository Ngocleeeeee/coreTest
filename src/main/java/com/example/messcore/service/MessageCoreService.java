package com.example.messcore.service;

import com.example.messcore.dto.MessageDTO;
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
    public void processMessage(MessageDTO messageDTO,String queueName) {
        UUID conversationId = messageDTO.getRelationship().getConversation().getId();
        UUID customerId = messageDTO.getRelationship().getCustomer().getId();
        UUID staffId = messageDTO.getRelationship().getStaff().getId();

        // Kiểm tra hội thoại có tồn tại chưa
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseGet(() -> {
                    Conversation newConversation = new Conversation();
                    newConversation.setId(conversationId);
                    newConversation.setActive((byte) 1);
                    newConversation.setPropertyId(conversationId);
                    newConversation.setPropertyType("test");
                    newConversation.setSortIndex(1);
                    newConversation.setVersion((long) 1.0);
                    newConversation.setIsClose(false);
                    return conversationRepository.save(newConversation);
                });


        // Lưu tin nhắn vào database
        Message message = new Message();
        message.setConversationId(conversation.getId());
        message.setContent(messageDTO.getContent());
        message.setExternalMessageCode(messageDTO.getExternalMessageCode());
        message.setFromAi(messageDTO.getFromAi());
        message.setContentType(message.getContentType());
        message.setIsProperty(messageDTO.getIsProperty());
        message.setIsRead(messageDTO.getIsRead());
        message.setUpdatedDate(messageDTO.getUpdatedDate());
//        message.setCustomerId(customerId);
//        message.setStaffId(staffId);
        message.setActive((byte) 1);
        message.setVersion((long) 1.0);
        message.setSortIndex(1);
        messageRepository.save(message);

        // Đẩy tin nhắn ra Queue B
        rabbitTemplate.convertAndSend(queueName, messageDTO);
    }
}
