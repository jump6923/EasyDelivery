package com.sparta.easydelivery.order.dto;

import com.sparta.easydelivery.order.entity.Order;
import com.sparta.easydelivery.order.entity.OrderProduct;
import com.sparta.easydelivery.order.entity.OrderStatusEnum;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public class OrderDetailResponseDto {
    private Long orderId;

    private String paymentMethod;

    private Long totalPrice;

    private String requests;

    private LocalDateTime createdAt;

    private OrderStatusEnum status;

    private String address;

    private String username;

    private Map<String, Integer> productMap = new HashMap<>();

    public OrderDetailResponseDto(Order order) {
        this.orderId = order.getId();
        this.paymentMethod = order.getPaymentMethod();
        this.totalPrice = order.getTotalPrice();
        this.requests = order.getRequests();
        this.createdAt = order.getCreatedAt();
        this.status = order.getStatus();
        this.address = order.getAddress();
        this.username = order.getUser().getUsername();
        for(OrderProduct orderProduct : order.getOrderProductList()){
            productMap.put(orderProduct.getProduct().getName(), orderProduct.getQuantity());
        }
    }
}
