package com.example.messcore.messsage.service;

import com.example.messcore.customer.repository.CustomerRepository;
import com.example.messcore.messsage.dto.MessageWrapper;
import com.example.messcore.messsage.repository.BookingRepository;

import com.example.messcore.messsage.repository.ConversationRepository;
import com.example.messcore.messsage.repository.MessageRepository;
import com.example.messcore.staff.repository.StaffRepository;
import ezcloud.message.messenger.Conversation;
import ezcloud.message.messenger.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class MessageCoreService {
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final RabbitTemplate rabbitTemplate;
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;
    private final StaffRepository staffRepository;

    @Transactional
    public void processMessage(MessageWrapper.MessageData messageDTO, String queueName) {
        UUID staffId = getSafeValue(() -> messageDTO.getRelationships().getStaff().getId());
        UUID bookingID = getSafeValue(() -> messageDTO.getRelationships().getBooking().getId());
        UUID customerId = getSafeValue(() -> messageDTO.getRelationships().getCustomer().getId());
        UUID propertyId = getSafeValue(() -> messageDTO.getRelationships().getProperty().getId());
        String propertyType = getSafeValue(() -> messageDTO.getRelationships().getProperty().getType());



        // Kiểm tra hội thoại có tồn tại chưa
        Conversation conversation = conversationRepository.findByCustomerId(customerId)
                .orElseGet(() -> {
                    Conversation newConversation = new Conversation();
                    newConversation.setActive((byte) 1);
                    newConversation.setPropertyId(propertyId);
                    newConversation.setPropertyType(propertyType);
                    newConversation.setCustomer(customerRepository.findCustomerById(customerId));
                    newConversation.setClose(false);
                    newConversation.setSortIndex(1);
                    newConversation.setBooking(bookingRepository.findById(bookingID).orElse(null));
                    return conversationRepository.save(newConversation);
                });


        // Lưu tin nhắn vào database
        Message message = new Message();
        message.setConversation(conversation);
        message.setContent(messageDTO.getAttributes().getContent());
        message.setExternalMessageCode(messageDTO.getAttributes().getExternalMessageCode());
        //message.setFromAi(messageDTO.getAttributes().getFromAi());
        message.setContentType(message.getContentType());
        message.setProperty(messageDTO.getAttributes().getIsProperty());
        message.setRead(messageDTO.getAttributes().getIsRead());
        message.setUpdatedDate(messageDTO.getAttributes().getUpdatedDate());
        message.setCustomer(customerRepository.findCustomerById(customerId));
        message.setStaff(staffRepository.findStaffById(staffId));
        messageRepository.save(message);
        conversation.setLastMessageId(message.getId());
        conversationRepository.save(conversation);
        rabbitTemplate.convertAndSend("hotel_roomrate_exchange",queueName, messageDTO);

    }
    private static <T> T getSafeValue(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (NullPointerException e) {
            return null;
        }
    }

}
