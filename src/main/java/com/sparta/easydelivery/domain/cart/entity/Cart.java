package com.sparta.easydelivery.domain.cart.entity;

import com.sparta.easydelivery.common.TimeStamp;
import com.sparta.easydelivery.domain.product.entity.Product;
import com.sparta.easydelivery.domain.product.exception.NotEnoughStockException;
import com.sparta.easydelivery.domain.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends TimeStamp {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @JoinColumn(nullable = false)
    private int quantity;

    private Cart(User user, Product product, int quantity) {
        product.checkDeleted();
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        product.checkStock(quantity);
    }

    public static Cart create(User user, Product product, int quantity) {
        return new Cart(user, product, quantity);
    }

    public void quantityUpdate(int quantity) {
        this.quantity = quantity;
    }
}
