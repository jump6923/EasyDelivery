package com.sparta.easydelivery.user.dto;

import com.sparta.easydelivery.user.entity.User;
import com.sparta.easydelivery.user.entity.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private String introduce;
    private String address;
    private UserRoleEnum role;
    private boolean blocked;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.introduce = user.getIntroduce();
        this.address = user.getAddress();
        this.role = user.getRole();
        this.blocked = user.isBlocked();
    }
}
