package com.sparta.easydelivery.user.dto;

import lombok.Getter;

@Getter
public class BlockResponseDto {
    private boolean blocked;
    public BlockResponseDto(boolean blocked) {this.blocked = blocked;}
}
