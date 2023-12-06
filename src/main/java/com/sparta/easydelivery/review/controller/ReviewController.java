package com.sparta.easydelivery.review.controller;

import com.sparta.easydelivery.review.dto.ReviewResponseDto;
import com.sparta.easydelivery.review.dto.ReviewUpdateRequestDto;
import com.sparta.easydelivery.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

//    @PostMapping("")
//    public ResponseEntity<ReviewResponseDto> createReview(
//        @RequestBody ReviewRequestDto requestDto,
//        @AuthenticationPrincipal UserDetailsImpl userDetails) {
//
//        ReviewResponseDto responseDto = reviewService
//            .createReview(requestDto, requestDto.getOrderId(), userDetails.getUser());
//        return ResponseEntity.ok(responseDto);
//    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDto> updateReview(
        @PathVariable Long reviewId,
        @RequestBody ReviewUpdateRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        ReviewResponseDto responseDto = reviewService
            .updateReview(requestDto, reviewId, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

}
