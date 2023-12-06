package com.sparta.easydelivery.order.entity;

import com.sparta.easydelivery.cart.entity.Cart;
import com.sparta.easydelivery.common.TimeStamp;
import com.sparta.easydelivery.order.dto.OrderRequestDto;
import com.sparta.easydelivery.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "orders")
@NoArgsConstructor
@Entity
public class Order extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private int totalPrice=0;

    @Column
    private String requests;

    @Column(nullable = false)
    private OrderStatusEnum status = OrderStatusEnum.PREPARATION;

    @Column(nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    public Order(OrderRequestDto requestDto, User user) {
        this.paymentMethod = requestDto.getPaymentMethod();
        this.requests = requestDto.getRequests();
        this.address = requestDto.getAddress();
        setUser(user);
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setTotalPrice(List<Cart> cartList) {
        for(Cart cart : cartList){
            this.totalPrice += cart.getQuantity() * cart.getProduct().getPrice();
        }
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }
}
