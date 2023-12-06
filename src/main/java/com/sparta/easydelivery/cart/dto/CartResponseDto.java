package com.sparta.easydelivery.cart.dto;

import com.sparta.easydelivery.cart.entity.Cart;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponseDto {

    private Long id;
    private String name;
    private int price;
    private int quantity;

    public CartResponseDto(Cart cart) {
        this.id = cart.getId();
        this.name = cart.getProduct().getName();
        this.price = cart.getProduct().getPrice();
        this.quantity = cart.getQuantity();
    }
}
