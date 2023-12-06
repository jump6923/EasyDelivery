package com.sparta.easydelivery.cart.dto;

import com.sparta.easydelivery.cart.entity.Cart;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartListResponseDto {

    private List<CartResponseDto> cartList;

    public CartListResponseDto(List<Cart> carts) {
        this.cartList = carts.stream().map(CartResponseDto::new).toList();
    }
}
