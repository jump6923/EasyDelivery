package com.sparta.easydelivery.user.entity;

import com.sparta.easydelivery.user.dto.IntroduceRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String introduce;

    @Column(nullable = true)
    private String address;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column(nullable = false)
    private boolean blocked;

    private Long kakaoId;

    public User(String username, String password, String email, String introduce, String address, UserRoleEnum role, boolean blocked) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.introduce = introduce;
        this.address = address;
        this.role = role;
        this.blocked = blocked;
    }

    public void changeUserInfo(IntroduceRequestDto requestDto) {
        if (requestDto.getEmail() != null) {
            this.email = requestDto.getEmail();
        }

        if (requestDto.getIntroduce() != null) {
            this.introduce = requestDto.getIntroduce();
        }

        if (requestDto.getAddress() != null) {
            this.address = requestDto.getAddress();
        }
    }

    private User(String username, String password, String email, Long kakaoId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.kakaoId = kakaoId;
        this.role = UserRoleEnum.USER;
    }

    public static User kakaoSignup(
        String username, String password, String email, Long kakaoId) {

        return new User(username, password, email, kakaoId);
    }

    public void kakaoIntegration(Long kakaoId) {
        this.kakaoId = kakaoId;
    }
}