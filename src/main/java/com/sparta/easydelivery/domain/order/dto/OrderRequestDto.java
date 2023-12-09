package com.sparta.easydelivery.domain.order.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class OrderRequestDto {
    @NotBlank
    String paymentMethod;

    String requests;

    @NotBlank
    String address;
}
