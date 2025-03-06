package com.example.messcore.rabbitmq.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelQueueManager {

    private final RabbitAdmin rabbitAdmin;

    private static final String EXCHANGE_NAME = "hotel_roomrate_exchange";
    private static final String DEAD_LETTER_EXCHANGE = "hotel_roomrate_dead_exchange";

    public void createQueueForHotel(UUID hotelId) {
        // Danh sách queue cần tạo
        String[] queueTypes = {"gateway_in", "gateway_out", "extranet_in", "extranet_out"};

        for (String type : queueTypes) {
            String queueName = "hotel_" + type + "_queue_" + hotelId;
            String deadQueueName = "hotel_" + type + "_dead_queue_" + hotelId;
            String routingKey = "hotel_" + type + "_" + hotelId;
            String deadRoutingKey = "dead_" + routingKey;

            // Kiểm tra queue có tồn tại không
            if (rabbitAdmin.getQueueProperties(queueName) == null) {
                // Cấu hình Dead Letter Exchange (DLX)
                Map<String, Object> args = new HashMap<>();
                args.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
                args.put("x-dead-letter-routing-key", deadRoutingKey);

                // Tạo queue chính và queue chết
                Queue queue = new Queue(queueName, true, false, false, args);
                Queue deadQueue = new Queue(deadQueueName, true);

                // Khai báo queue với RabbitMQ
                rabbitAdmin.declareQueue(queue);
                rabbitAdmin.declareQueue(deadQueue);

                // Bind queue chính vào Exchange
                rabbitAdmin.declareBinding(BindingBuilder.bind(queue)
                        .to(new DirectExchange(EXCHANGE_NAME))
                        .with(routingKey));

                // Bind queue chết vào Dead Letter Exchange
                rabbitAdmin.declareBinding(BindingBuilder.bind(deadQueue)
                        .to(new DirectExchange(DEAD_LETTER_EXCHANGE))
                        .with(deadRoutingKey));

                System.out.println("Queue created: " + queueName);
            }
        }
    }

}
