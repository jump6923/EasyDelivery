package com.sparta.easydelivery.user.dto;


import com.sparta.easydelivery.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileResponseDto {
    private String username;
    private String email;
    private String introduce;
    private String address;

    public ProfileResponseDto(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.introduce = user.getIntroduce();
        this.address = user.getAddress();
    }
}
