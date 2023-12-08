package com.sparta.easydelivery.order.dto;

import com.sparta.easydelivery.order.entity.Order;
import com.sparta.easydelivery.order.entity.OrderStatusEnum;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class OrderResponseDto {
    private Long orderId;

    private String paymentMethod;

    private Long totalPrice;

    private String requests;

    private LocalDateTime createdAt;

    private OrderStatusEnum status;

    private String address;

    private String username;

    public OrderResponseDto(Order saveOrder) {
        this.orderId = saveOrder.getId();
        this.paymentMethod = saveOrder.getPaymentMethod();
        this.totalPrice = saveOrder.getTotalPrice();
        this.requests = saveOrder.getRequests();
        this.createdAt = saveOrder.getCreatedAt();
        this.status = saveOrder.getStatus();
        this.address = saveOrder.getAddress();
        this.username = saveOrder.getUser().getUsername();
    }
}
