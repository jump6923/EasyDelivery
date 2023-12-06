package com.sparta.easydelivery.review.dto;

import lombok.Getter;

@Getter
public class ReviewRequestDto {

    private String content;

    private Float star;

    private Long orderId;

}
