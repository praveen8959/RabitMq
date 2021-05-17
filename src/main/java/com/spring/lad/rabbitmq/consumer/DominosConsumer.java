package com.spring.lad.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.spring.lad.rabbitmq.dto.OrderDetails;

@Component
public class DominosConsumer {

    @RabbitListener(queues = "${rabbit.queue.name}")
    public void getOrderStatus(OrderDetails orderDetails) {
        System.out.println("Message received : " + orderDetails);
    }

}
