package com.sparta.easydelivery.review.dto;

import com.sparta.easydelivery.review.entity.Review;
import lombok.Getter;

@Getter
public class ReviewResponseDto {

    private Long id;
    private String content;
    private Float star;
    private String username;

    public ReviewResponseDto(Review review) {
        this.id = review.getId();
        this.content = review.getContent();
        this.star = review.getStar();
        this.username = review.getUser().getUsername();
    }
}
