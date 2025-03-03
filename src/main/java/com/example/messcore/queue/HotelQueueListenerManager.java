package com.example.messcore.queue;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HotelQueueListenerManager {

    private final DirectMessageListenerContainer listenerContainer;
    private final MessageListenerAdapter messageListenerAdapter;
    private final RabbitAdmin rabbitAdmin;

    public void startListeningForHotel(UUID hotelId) {
        String[] queueTypes = {"gateway_in", "gateway_out", "extranet_in", "extranet_out"};

        for (String type : queueTypes) {
            String queueName = "hotel_" + type + "_queue_" + hotelId;

            if (rabbitAdmin.getQueueProperties(queueName) != null) {
                listenerContainer.addQueueNames(queueName);
                System.out.println("Started listening on queue: " + queueName);
            } else {
                System.out.println("Queue does not exist: " + queueName);
            }
        }
    }

    public void stopListeningForHotel(UUID hotelId) {
        String[] queueTypes = {"gateway_in", "gateway_out", "extranet_in", "extranet_out"};

        for (String type : queueTypes) {
            String queueName = "hotel_" + type + "_queue_" + hotelId;
            listenerContainer.removeQueueNames(queueName);
            System.out.println("Stopped listening on queue: " + queueName);
        }
    }

}
