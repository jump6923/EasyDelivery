package com.sparta.easydelivery.domain.review.entity;

import com.sparta.easydelivery.common.TimeStamp;
import com.sparta.easydelivery.domain.order.entity.Order;
import com.sparta.easydelivery.domain.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Review extends TimeStamp {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @JoinColumn(nullable = false)
    private Float star;

    @JoinColumn(nullable = false)
    private String content;

    private Review(User user, Float star, String content, Order order) {
        this.user = user;
        this.star = star;
        this.content = content;
        this.order = order;
    }

    public static Review create(User user, Float star, String content, Order order) {
        return new Review(user, star, content, order);
    }

    public void update(String content, Float star) {
        this.content = content;
        this.star = star;
    }
}
