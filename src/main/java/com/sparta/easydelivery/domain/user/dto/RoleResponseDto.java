package com.sparta.easydelivery.domain.user.dto;

import com.sparta.easydelivery.domain.user.entity.UserRoleEnum;
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
