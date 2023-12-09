package com.sparta.easydelivery.domain.review.dto;

import com.sparta.easydelivery.domain.review.entity.Review;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class ReviewListResponseDto {

    Page<ReviewResponseDto> reviewList;

    public ReviewListResponseDto(Page<Review> reviews) {
        reviewList = reviews.map(ReviewResponseDto::new);
    }
}
