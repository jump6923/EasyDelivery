package com.sparta.easydelivery.domain.order.dto;

import com.sparta.easydelivery.domain.order.entity.OrderStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OrderStatusRequestDto {

    @NotNull
    OrderStatusEnum orderStatusEnum;

}
