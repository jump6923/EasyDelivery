package com.sparta.easydelivery.domain.cart.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class CartUpdateRequestDto {

    @Min(value = 1, message = "수량은 최소 1 이상입니다.")
    private int quantity;

}
