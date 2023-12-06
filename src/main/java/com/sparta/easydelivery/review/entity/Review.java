package com.sparta.easydelivery.review.entity;

import com.sparta.easydelivery.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JoinColumn(nullable = false)
    private Float star;

    @JoinColumn(nullable = false)
    private String content;

    private Review(User user, Float star, String content) {
        this.user = user;
        this.star = star;
        this.content = content;
    }

    public static Review create(User user, Float star, String content) {
        return new Review(user, star, content);
    }
}
