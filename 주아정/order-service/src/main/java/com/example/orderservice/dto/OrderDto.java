package com.example.orderservice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDto implements Serializable {
    private String productId;
    private Integer qty; // 수량
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId; // 어떤 사람이 주문했는지
}
