package com.sparta.easydelivery.user.dto;

import com.sparta.easydelivery.user.entity.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponseDto {
    private UserRoleEnum role;

    public RoleResponseDto(UserRoleEnum role){
        this.role = role;
    }
}
