package com.example.messcore.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${queueGWIn.name}")
    private String queueGWIn;

    @Value("${queueGWOut.name}")
    private String queueGWOut;

    @Value("${queueExIn.name}")
    private String queueExIn;

    @Value("${queueExOut.name}")
    private String queueExOut;

    @Bean
    public Queue queueGWIn() {
        return new Queue(queueGWIn, true);
    }

    @Bean
    public Queue queueGWOut() {
        return new Queue(queueGWOut, true);
    }

    @Bean
    public Queue queueExIn() {
        return new Queue(queueExIn, true);
    }

    @Bean
    public Queue queueExOut() {
        return new Queue(queueExOut, true);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }
}
