package com.sparta.easydelivery.domain.cart.dto;

import com.sparta.easydelivery.domain.cart.entity.Cart;
import java.util.List;
import lombok.Getter;

@Getter
public class CartListResponseDto {

    private List<CartResponseDto> cartList;

    public CartListResponseDto(List<Cart> carts) {
        this.cartList = carts.stream().map(CartResponseDto::new).toList();
    }
}
