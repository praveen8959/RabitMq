package com.spring.lad.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.val;

@Configuration
public class MqConfig {

    @Value("${rabbit.queue.name}")
    private String queueName;

    @Value("${rabbit.queue.exchange}")
    private String queueExchange;

    @Value("${rabbit.queue.routingkey}")
    private String routingKey;

    @Bean
    public Queue getQueue() {
        return new Queue(queueName);
    }

    @Bean
    public TopicExchange getExchange() {
        return new TopicExchange(queueExchange);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(getQueue()).to(getExchange()).with(routingKey);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        val rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

}
