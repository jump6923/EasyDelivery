package com.sparta.easydelivery.user.entity;

import com.sparta.easydelivery.user.dto.IntroduceRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;

    private String introduce;

    private String address;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column(nullable = false)
    private boolean blocked;

    private Long kakaoId;

    private String naverId;

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

    private User(String username, String password, String email, String naverId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.naverId = naverId;
        this.role = UserRoleEnum.USER;
    }

    public static User kakaoSignup(
        String username, String password, String email, Long kakaoId) {

        return new User(username, password, email, kakaoId);
    }
    public static User naverSignup(
        String username, String password, String email, String naverId) {

        return new User(username, password, email, naverId);
    }

    public void kakaoIntegration(Long kakaoId) {
        this.kakaoId = kakaoId;
    }

    public void naverIntegration(String naverId) {
        this.naverId = naverId;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public boolean changeAccess() {
        blocked = !blocked;
        return blocked;
    }

    public UserRoleEnum changeRole() {
        if (role == UserRoleEnum.ADMIN) {
            role = UserRoleEnum.USER;
            return role;
        }
        role = UserRoleEnum.ADMIN;
        return role;
    }
}