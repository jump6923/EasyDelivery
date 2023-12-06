package com.sparta.easydelivery.review.entity;

import com.sparta.easydelivery.review.temp.Order;
import com.sparta.easydelivery.review.temp.User;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JoinColumn(nullable = false)
    private Float star;

    @JoinColumn(nullable = false)
    private String content;

    private Review(User user, Float star, String content, Order order) {
        this.user = user;
        this.star = star;
        this.content = content;
        order.addReview(this);
    }

    public static Review create(User user, Float star, String content, Order order) {
        return new Review(user, star, content, order);
    }
}
