package com.sparta.easydelivery.order.dto;

import com.sparta.easydelivery.order.entity.OrderStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OrderStatusRequestDto {

    @NotNull
    OrderStatusEnum orderStatusEnum;

}
