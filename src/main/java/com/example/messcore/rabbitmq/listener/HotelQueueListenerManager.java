package com.example.messcore.rabbitmq.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HotelQueueListenerManager {

    private final RabbitAdmin rabbitAdmin;

    @Qualifier("gatewayListenerContainer")
    private final DirectMessageListenerContainer gatewayListenerContainer;

    @Qualifier("extranetListenerContainer")
    private final DirectMessageListenerContainer extranetListenerContainer;

    public void startListeningForHotel(UUID hotelId) {
        String gatewayQueue = "hotel_gateway_in_queue_" + hotelId;
        String extranetQueue = "hotel_extranet_in_queue_" + hotelId;

        if (rabbitAdmin.getQueueProperties(gatewayQueue) != null) {
            gatewayListenerContainer.addQueueNames(gatewayQueue);
            System.out.println("Started listening on queue: " + gatewayQueue);
        }

        if (rabbitAdmin.getQueueProperties(extranetQueue) != null) {
            extranetListenerContainer.addQueueNames(extranetQueue);
            System.out.println("Started listening on queue: " + extranetQueue);
        }
    }

    public void stopListeningForHotel(UUID hotelId) {
        String gatewayQueue = "hotel_gateway_in_queue_" + hotelId;
        String extranetQueue = "hotel_extranet_in_queue_" + hotelId;

        gatewayListenerContainer.removeQueueNames(gatewayQueue);
        extranetListenerContainer.removeQueueNames(extranetQueue);
        System.out.println("Stopped listening on queues for hotel: " + hotelId);
    }
}
