package com.sparta.easydelivery.order.dto;

import com.sparta.easydelivery.order.entity.Order;
import java.util.List;
import lombok.Getter;

@Getter
public class OrderListResponseDto {
    private List<OrderResponseDto> orderList;

    public OrderListResponseDto(List<Order> orderList) {
        this.orderList = orderList.stream().map(OrderResponseDto::new).toList();
    }
}
