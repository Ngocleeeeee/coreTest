package com.example.messcore.messsage.service;

import com.example.messcore.customer.repository.CustomerRepository;
import com.example.messcore.messsage.dto.MessageWrapper;
import com.example.messcore.messsage.repository.BookingRepository;
import com.example.messcore.messsage.repository.ConversationRepository;
import com.example.messcore.messsage.repository.MessageRepository;
import com.example.messcore.staff.repository.StaffRepository;
import ezcloud.message.booking.Booking;
import ezcloud.message.booking.Customer;
import ezcloud.message.messenger.Conversation;
import ezcloud.message.messenger.Message;
import ezcloud.message.staff.Staff;
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

        if (customerId == null) {
            throw new IllegalArgumentException("Customer ID không được null");
        }

        Conversation conversation = conversationRepository.findByCustomerId(customerId)
                .orElseGet(() -> {
                    Conversation newConversation = new Conversation();
                    newConversation.setActive((byte) 1);
                    newConversation.setPropertyId(propertyId);
                    newConversation.setPropertyType(propertyType);
                    newConversation.setClose(false);
                    newConversation.setSortIndex(1);

                    Customer customer = customerRepository.findCustomerById(customerId);
                    if (customer == null) {
                        throw new IllegalArgumentException("Không tìm thấy customer với ID: " + customerId);
                    }
                    newConversation.setCustomer(customer);

                    Booking booking = bookingID != null ? bookingRepository.findById(bookingID).orElse(null) : null;
                    newConversation.setBooking(booking);

                    return conversationRepository.save(newConversation);
                });

        Message message = new Message();
        message.setConversation(conversation);
        message.setContent(messageDTO.getAttributes().getContent());
        message.setExternalMessageCode(messageDTO.getAttributes().getExternalMessageCode());
        // message.setFromAi(messageDTO.getAttributes().getFromAi());
        message.setContentType(message.getContentType());
        message.setProperty(messageDTO.getAttributes().getIsProperty());
        message.setRead(messageDTO.getAttributes().getIsRead());
        message.setUpdatedDate(messageDTO.getAttributes().getUpdatedDate());

        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Không tìm thấy customer với ID: " + customerId);
        }
        message.setCustomer(customer);

        Staff staff = staffId != null ? staffRepository.findStaffById(staffId) : null;
        message.setStaff(staff);

        messageRepository.save(message);

        conversation.setLastMessageId(message.getId());
        conversationRepository.save(conversation);

        rabbitTemplate.convertAndSend("hotel_roomrate_exchange", queueName, messageDTO);
    }


    private static <T> T getSafeValue(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (NullPointerException e) {
            return null;
        }
    }

}
