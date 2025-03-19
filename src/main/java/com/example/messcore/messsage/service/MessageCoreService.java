package com.example.messcore.messsage.service;

import com.example.messcore.customer.repository.CustomerRepository;
import com.example.messcore.customer.repository.OtaRepository;
import com.example.messcore.hotel.dto.HotelDto;
import com.example.messcore.hotel.repository.HotelRepository;
import com.example.messcore.messsage.dto.ContentDto;
import com.example.messcore.messsage.dto.MessageWrapper;
import com.example.messcore.messsage.dto.SenderDto;
import com.example.messcore.messsage.repository.BookingRepository;
import com.example.messcore.messsage.repository.ConversationRepository;
import com.example.messcore.messsage.repository.MessageRepository;
import com.example.messcore.staff.repository.StaffRepository;
import ezcloud.message.booking.Booking;
import ezcloud.message.booking.Customer;
import ezcloud.message.booking.CustomerType;
import ezcloud.message.hotel.Hotel;
import ezcloud.message.messenger.Conversation;
import ezcloud.message.messenger.Message;
import ezcloud.message.ota.OTA;
import ezcloud.message.staff.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class MessageCoreService {
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final RabbitTemplate rabbitTemplate;
    private final FirebaseService firebaseService;
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;
    private final OtaRepository otaRepository;
    private final StaffRepository staffRepository;

    private static <T> T getSafeValue(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Transactional
    public void processMessage(MessageWrapper.MessageData messageDTO, String queueName) {
        UUID staffId = getSafeValue(() -> messageDTO.getRelationships().getStaff().getId());
        UUID bookingID = getSafeValue(() -> messageDTO.getRelationships().getBooking().getId());
        UUID customerId = getSafeValue(() -> messageDTO.getRelationships().getCustomer().getId());
        UUID propertyId = getSafeValue(() -> messageDTO.getRelationships().getProperty().getId());
        UUID otaId = getSafeValue(() -> messageDTO.getRelationships().getConversation().getOtaId());

        String propertyType = getSafeValue(() -> messageDTO.getRelationships().getProperty().getType());

        if (customerId == null) {
            throw new IllegalArgumentException("Customer ID không được null");
        }

        if (propertyId == null) {
            throw new IllegalArgumentException("Property ID không được null");
        }

        HotelDto hotelDto = new HotelDto();
        SenderDto senderDto= new SenderDto();
        ContentDto contentDto = new ContentDto();

        Optional<Hotel> hotel = hotelRepository.findById(propertyId);
        if(hotel.isPresent()){
            hotelDto.setHotelCode(hotel.get().getHotelCode());
            hotelDto.setEmail(hotel.get().getEmail());
            hotelDto.setNameVi(hotel.get().getNameVi());
        }

        Conversation conversation = conversationRepository.findByCustomerIdAndPropertyId(customerId, propertyId)
                .orElseGet(() -> {
                    Conversation newConversation = new Conversation();
                    newConversation.setActive((byte) 1);
                    newConversation.setPropertyId(propertyId);
                    newConversation.setPropertyType(propertyType);
                    newConversation.setClosed(false);
                    newConversation.setSortIndex(1);

                    Customer customer = customerRepository.findCustomerById(customerId);
                    if (customer == null) {
                        throw new IllegalArgumentException("Không tìm thấy customer với ID: " + customerId);
                    }
                    newConversation.setCustomer(customer);

                    OTA ota = otaId != null ? otaRepository.findById(otaId).orElse(null) : null;
                    newConversation.setOtaId(ota);

                    Booking booking = bookingID != null ? bookingRepository.findById(bookingID).orElse(null) : null;
                    newConversation.setBooking(booking);

                    return conversationRepository.save(newConversation);
                });
        Message message = new Message();

        try {
            message.setConversation(conversation);
            message.setContent(messageDTO.getAttributes().getContent());
            message.setExternalMessageCode(messageDTO.getAttributes().getExternalMessageCode());
            message.setContentType(message.getContentType());
            message.setRead(false);
            message.setUpdatedDate(messageDTO.getAttributes().getUpdatedDate());

            Customer customer = customerRepository.findCustomerById(customerId);
            if (customer == null) {
                throw new IllegalArgumentException("Không tìm thấy customer với ID: " + customerId);
            }
            Staff staff = staffId != null ? staffRepository.findStaffById(staffId) : null;
            if (queueName.contains("extranet_out")) {
                message.setSentBy(CustomerType.CUSTOMER.getValue());
                message.setCreatedBy(customerId);
                senderDto.setId(customerId);
                senderDto.setEmail(customer.getEmail());
                senderDto.setSenderType(CustomerType.CUSTOMER);
            } else if (queueName.contains("gateway_out")) {
                message.setSentBy(CustomerType.STAFF.getValue());
                message.setCreatedBy(staffId);
                senderDto.setId(staffId);
                senderDto.setEmail(staff != null ? staff.getEmail() : null);
                senderDto.setSenderType(CustomerType.STAFF);
            }
            messageRepository.save(message);
            conversation.setLastMessageId(message.getId());
            conversationRepository.save(conversation);
            messageDTO.getAttributes().setId(message.getId());
            contentDto.setContent(message.getContent());
            contentDto.setId(message.getId());
            contentDto.setContentType(message.getContentType());
            firebaseService.updateMessageStatus("messages", "SENT",hotelDto,senderDto,contentDto);

            rabbitTemplate.convertAndSend("hotel_roomrate_exchange", queueName, messageDTO);
        } catch (Exception e) {
            firebaseService.updateMessageStatus("messages","FAILED",hotelDto,senderDto,contentDto);
            throw new RuntimeException("Lưu tin nhắn thất bại", e);
        }

    }
    @Transactional
    public void updateMessagesReadStatus(List<UUID> messageIds, boolean isRead) {
        List<Message> messages = messageRepository.findAllById(messageIds);

        HotelDto hotelDto = new HotelDto();
        SenderDto senderDto= new SenderDto();
        ContentDto contentDto = new ContentDto();

        if (messages.isEmpty()) {
            throw new IllegalArgumentException("Không tìm thấy message với các ID đã cung cấp");
        }



        messages.forEach(message -> message.setRead(isRead));
        messageRepository.saveAll(messages);

        for (Message message : messages) {
            Conversation conversation = message.getConversation();
            Optional<Hotel> hotel = hotelRepository.findById(conversation.getPropertyId());
            if(hotel.isPresent()){
                hotelDto.setHotelCode(hotel.get().getHotelCode());
                hotelDto.setEmail(hotel.get().getEmail());
                hotelDto.setNameVi(hotel.get().getNameVi());
            }

            senderDto.setId(message.getCreatedBy());
            if(message.getSentBy()==CustomerType.CUSTOMER.getValue() ){
                Optional<Customer> customer=customerRepository.findById(message.getCreatedBy());
                senderDto.setEmail(customer.get().getEmail());
                senderDto.setSenderType(CustomerType.CUSTOMER);
            } else if (message.getSentBy()==CustomerType.STAFF.getValue()) {
                Optional<Staff> staff=staffRepository.findById(message.getCreatedBy());
                senderDto.setEmail(staff.get().getEmail());
                senderDto.setSenderType(CustomerType.STAFF);
            }else{
                Optional<Customer> customer=customerRepository.findById(message.getCreatedBy());
                senderDto.setEmail(customer.get().getEmail());
                senderDto.setSenderType(CustomerType.GUEST);
            }
            contentDto.setId(message.getId());
            contentDto.setContent(message.getContent());
            contentDto.setContentType(message.getContentType());

            String status = isRead ? "READ" : "UNREAD";
            firebaseService.updateMessageStatus("messagesStatus", status,hotelDto,senderDto,contentDto);
        }
    }

}