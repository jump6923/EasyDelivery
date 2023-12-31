package com.sparta.easydelivery.domain.review.controller;

import com.sparta.easydelivery.domain.review.dto.ReviewListResponseDto;
import com.sparta.easydelivery.domain.review.dto.ReviewRequestDto;
import com.sparta.easydelivery.domain.review.dto.ReviewResponseDto;
import com.sparta.easydelivery.domain.review.dto.ReviewUpdateRequestDto;
import com.sparta.easydelivery.domain.review.service.ReviewService;
import com.sparta.easydelivery.domain.user.implement.UserDetailsImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
@Validated
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("")
    public ResponseEntity<ReviewResponseDto> createReview(
        @Valid @RequestBody ReviewRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        ReviewResponseDto responseDto = reviewService
            .createReview(requestDto, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDto> updateReview(
        @PathVariable Long reviewId,
        @Valid @RequestBody ReviewUpdateRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        ReviewResponseDto responseDto = reviewService
            .updateReview(requestDto, reviewId, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(
        @PathVariable Long reviewId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {

        reviewService.deleteReview(reviewId, userDetails.getUser());
        return ResponseEntity.ok().build();
    }

    /**
     * @RequestParam으로 받는 값의 검증을 위해서는 클래스 위에 @Validated 적용해야 함
     * 발생하는 예외는 MethodArgumentNotValidException가 아닌
     * ConstraintViolationException <- 이것
     */
    @GetMapping("")
    public ResponseEntity<ReviewListResponseDto> getReviewList(
        @RequestParam(defaultValue = "1") @Positive(message = "페이지는 양수만 가능합니다.") int page,
        @RequestParam(defaultValue = "10") @Min(value = 10, message = "페이지당 리뷰는 최소 10입니다.")
        @Max(value = 50, message = "페이지당 리뷰는 최대 50입니다.") int size,
        @RequestParam(defaultValue = "createdAt") String sortBy,
        @RequestParam(defaultValue = "false") String flag) {

        if (!flag.equals("true") && !flag.equals("false")) {
            throw new IllegalArgumentException("flag 값은 'true' 또는 'false'를 입력해 주세요");
        }

        if (!sortBy.equals("createdAt") && !sortBy.equals("star")) {
            throw new IllegalArgumentException("정렬 방식은 'createAt' 또는 'star'만 가능합니다.");
        }

        boolean isAsc = flag.equals("true") ? true : false;

        ReviewListResponseDto responseDto = reviewService
            .getReviewList(page-1, size, sortBy, isAsc);
        return ResponseEntity.ok(responseDto);
    }

}
