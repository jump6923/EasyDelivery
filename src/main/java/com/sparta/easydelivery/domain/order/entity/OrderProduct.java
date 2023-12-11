package com.sparta.easydelivery.domain.order.entity;

import com.sparta.easydelivery.domain.cart.entity.Cart;
import com.sparta.easydelivery.common.TimeStamp;
import com.sparta.easydelivery.domain.product.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "order_product")
@NoArgsConstructor
@Entity
public class OrderProduct extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    Product product;

    public OrderProduct(Cart cart, Order order) {
        Product product = cart.getProduct();
        product.checkDeleted();
        this.quantity = cart.getQuantity();
        this.product = product;
        this.order = order;
        product.getOrderProduct().add(this);
        product.reduceStock(quantity);
    }
}
