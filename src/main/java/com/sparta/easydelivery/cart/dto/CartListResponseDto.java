package com.sparta.easydelivery.cart.dto;

import com.sparta.easydelivery.cart.entity.Cart;
import java.util.List;
import lombok.Getter;

@Getter
public class CartListResponseDto {

    private List<CartResponseDto> cartList;

    public CartListResponseDto(List<Cart> carts) {
        this.cartList = carts.stream().map(CartResponseDto::new).toList();
    }
}
