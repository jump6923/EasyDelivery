package com.sparta.easydelivery.cart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CartRequestDto {

    @NotNull(message = "상품id값은 필수 입니다.")
    private Long productId;

    @Min(value = 1, message = "수량은 최소 1 이상입니다.")
    private int quantity;

}
