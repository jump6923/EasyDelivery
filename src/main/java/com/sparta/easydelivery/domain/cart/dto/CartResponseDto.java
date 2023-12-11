package com.sparta.easydelivery.domain.cart.dto;

import com.sparta.easydelivery.domain.cart.entity.Cart;
import lombok.Getter;

@Getter
public class CartResponseDto {

    private Long id;
    private String name;
    private Long price;
    private int quantity;
    private boolean isOutOfStock = false;
    private boolean isDeleted;

    public CartResponseDto(Cart cart) {
        this.id = cart.getId();
        this.name = cart.getProduct().getName();
        this.quantity = cart.getQuantity();
        this.price = cart.getProduct().getPrice() * quantity;
        if (cart.getProduct().getStock() - quantity < 0) {
            isOutOfStock = true;
        }
        this.isDeleted = cart.getProduct().isDeleted();
    }
}
