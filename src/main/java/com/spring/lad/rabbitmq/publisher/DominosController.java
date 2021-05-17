package com.spring.lad.rabbitmq.publisher;

import java.util.Random;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.lad.rabbitmq.dto.OrderDetails;
import com.spring.lad.rabbitmq.dto.OrderStatus;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class DominosController {

    private final RabbitTemplate template;

    @Value("${rabbit.queue.exchange}")
    private String               exchange;

    @Value("${rabbit.queue.routingkey}")
    private String               routingKey;

    @PostMapping("/{vegOrNonVeg}")
    public OrderStatus orderPizza(@RequestBody OrderDetails orderDetails, @PathVariable String vegOrNonVeg) {
        orderDetails.setOrderId(new Random().nextInt(Integer.SIZE - 1));
        template.convertAndSend(exchange, routingKey, orderDetails);
        return OrderStatus.builder()
                .message(vegOrNonVeg + " Pizza order confirmed")
                .status("SUCCESS")
                .order(orderDetails)
                .build();
    }

}
