package com.sparta.easydelivery.user.entity;

import com.sparta.easydelivery.user.dto.IntroduceRequestDto;
import com.sparta.easydelivery.user.dto.PasswordRequestDto;
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

    public User(String username, String password, String email, String introduce, String address, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.introduce = introduce;
        this.address = address;
        this.role = role;
    }

    public void changeUserInfo(IntroduceRequestDto requestDto) {
        if (requestDto.getEmail() != null) {
            this.email = requestDto.getEmail();
        }

        if (requestDto.getIntroduce() != null) {
            this.introduce = requestDto.getIntroduce();
        }

        if (requestDto.getAddress() != null) {
            this.introduce = requestDto.getAddress();
        }
    }

    public void changePassword(PasswordRequestDto requestDto) {
        if (requestDto.getChangePassword() != null) {
            this.password = requestDto.getChangePassword();
        }
    }

}