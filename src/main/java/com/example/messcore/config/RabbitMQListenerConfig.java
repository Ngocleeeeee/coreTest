package com.example.messcore.config;


import com.example.messcore.queue.HotelQueueMessageHandler;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQListenerConfig {

    @Bean
    public DirectMessageListenerContainer directMessageListenerContainer(
            ConnectionFactory connectionFactory,
            MessageListenerAdapter messageListenerAdapter) {
        DirectMessageListenerContainer container = new DirectMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setMessageListener(messageListenerAdapter);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return container;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(HotelQueueMessageHandler messageHandler) {
        return new MessageListenerAdapter(messageHandler, "handleMessage");
    }
}
