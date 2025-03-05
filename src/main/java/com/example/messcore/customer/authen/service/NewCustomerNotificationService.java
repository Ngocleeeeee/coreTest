package com.example.messcore.customer.authen.service;

import com.example.messcore.customer.authen.dto.GuestCustomerNotification;
import ezcloud.message.booking.CustomerType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NewCustomerNotificationService {

    private final RabbitTemplate rabbitTemplate;

    public NewCustomerNotificationService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${queueNotifiIn.name}")
    private String queueNotifiIn;

    public void sendGuestNotification(String email, String name, CustomerType role) {
        GuestCustomerNotification notification = new GuestCustomerNotification(email, name, name, role, "notification");
        rabbitTemplate.convertAndSend(queueNotifiIn, notification);
    }
}
