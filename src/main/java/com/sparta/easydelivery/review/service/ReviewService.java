package com.sparta.easydelivery.review.service;

import com.sparta.easydelivery.review.dto.ReviewListResponseDto;
import com.sparta.easydelivery.review.dto.ReviewRequestDto;
import com.sparta.easydelivery.review.dto.ReviewResponseDto;
import com.sparta.easydelivery.review.dto.ReviewUpdateRequestDto;
import com.sparta.easydelivery.review.entity.Review;
import com.sparta.easydelivery.review.repository.ReviewRepository;
import com.sparta.easydelivery.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;

    public ReviewResponseDto createReview(ReviewRequestDto requestDto, User user) {
        Order order = getUserOrder(requestDto.getOrderId(), user);
        if (reviewRepository.existsByOrder(order)) {
            throw new IllegalArgumentException("해당 주문은 리뷰가 이미 존재합니다.");
        }

        Review review = Review.create(user, requestDto.getStar(), requestDto.getContent(), order);
        reviewRepository.save(review);
        return new ReviewResponseDto(review);
    }

    public ReviewResponseDto updateReview(ReviewUpdateRequestDto requestDto, Long reviewId, User user) {
        Review review = getUserReview(reviewId, user);
        review.update(requestDto.getContent(), requestDto.getStar());
        return new ReviewResponseDto(review);
    }

    public void deleteReview(Long reviewId, User user) {
        Review review = getUserReview(reviewId, user);
        reviewRepository.delete(review);
    }

    /**
     * 존재하는 주문인지, 리뷰를 남기려는 사용자의 주문이 맞는지 확인하는 메소드
     */
    private Order getUserOrder(Long orderId, User user) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다."));
        if (!order.getUser().getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException("해당 유저의 주문이 아닙니다.");
        }
        return order;
    }

    private Review getUserReview(Long reviewId, User user) {
        Review review = reviewRepository.findById(reviewId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 리뷰입니다."));
        if (!review.getUser().getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException("본인의 리뷰가 아닙니다.");
        }
        return review;
    }

    public ReviewListResponseDto getReviewList(int page, int size, String sortBy, Boolean isAsc) {
        Sort.Direction direction =isAsc ? Direction.ASC : Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Review> reviewList = reviewRepository.findAll(pageable);
        return new ReviewListResponseDto(reviewList);
    }
}
