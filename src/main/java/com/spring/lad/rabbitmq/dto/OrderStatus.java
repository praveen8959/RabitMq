package com.spring.lad.rabbitmq.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class OrderStatus {

    private OrderDetails order;
    private String       status;
    private String       message;

}
