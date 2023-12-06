package com.sparta.easydelivery.cart.dto;

import com.sparta.easydelivery.cart.entity.Cart;
import lombok.Getter;

@Getter
public class CartResponseDto {

    private Long id;
    private String name;
    private Long price;
    private int quantity;

    public CartResponseDto(Cart cart) {
        this.id = cart.getId();
        this.name = cart.getProduct().getName();
        this.quantity = cart.getQuantity();
        this.price = cart.getProduct().getPrice() * quantity;
    }
}
