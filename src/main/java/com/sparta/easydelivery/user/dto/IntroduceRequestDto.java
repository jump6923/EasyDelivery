package com.sparta.easydelivery.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntroduceRequestDto {
    private String email;
    private String introduce;
    private String address;
}