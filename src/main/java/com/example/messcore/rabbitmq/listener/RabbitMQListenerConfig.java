package com.example.messcore.rabbitmq.listener;

import com.example.messcore.rabbitmq.config.ExtranetMessageHandler;
import com.example.messcore.rabbitmq.config.GatewayMessageHandler;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQListenerConfig {

    @Bean
    public DirectMessageListenerContainer gatewayListenerContainer(
            ConnectionFactory connectionFactory,
            @Qualifier("gatewayMessageListenerAdapter") MessageListenerAdapter gatewayAdapter) {
        DirectMessageListenerContainer container = new DirectMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setMessageListener(gatewayAdapter);
        return container;
    }

    @Bean
    public DirectMessageListenerContainer extranetListenerContainer(
            ConnectionFactory connectionFactory,
            @Qualifier("extranetMessageListenerAdapter") MessageListenerAdapter extranetAdapter) {
        DirectMessageListenerContainer container = new DirectMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setMessageListener(extranetAdapter);
        return container;
    }

    @Bean
    public MessageListenerAdapter gatewayMessageListenerAdapter(GatewayMessageHandler gatewayHandler) {
        return new MessageListenerAdapter(gatewayHandler, "handleMessage");
    }

    @Bean
    public MessageListenerAdapter extranetMessageListenerAdapter(ExtranetMessageHandler extranetHandler) {
        return new MessageListenerAdapter(extranetHandler, "handleMessage");
    }
}
