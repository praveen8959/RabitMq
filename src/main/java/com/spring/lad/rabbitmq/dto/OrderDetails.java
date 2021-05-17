package com.spring.lad.rabbitmq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderDetails {

    private int    orderId;
    private String itemName;
    private int    quantity;
    private double price;
}
