package com.sparta.easydelivery.user.entity;

import com.sparta.easydelivery.common.TimeStamp;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PasswordHistory extends TimeStamp {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JoinColumn(nullable = false)
    private String password;

    public PasswordHistory(User user, String password) {
        this.user = user;
        this.password = password;
    }
}