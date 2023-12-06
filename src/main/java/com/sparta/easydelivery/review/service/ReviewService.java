package com.sparta.easydelivery.review.service;

import com.sparta.easydelivery.review.dto.ReviewRequestDto;
import com.sparta.easydelivery.review.dto.ReviewResponseDto;
import com.sparta.easydelivery.review.entity.Review;
import com.sparta.easydelivery.review.repository.ReviewRepository;
import com.sparta.easydelivery.review.temp.Order;
import com.sparta.easydelivery.review.temp.OrderRepository;
import com.sparta.easydelivery.review.temp.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;

    public ReviewResponseDto createReview(ReviewRequestDto requestDto, Long orderId, User user) {
        Order order = getUserOrder(orderId, user);
        if (order.getReview() != null) {
            throw new IllegalArgumentException("이미 리뷰가 등록된 주문입니다.");
        }
        Review review = Review.create(user, requestDto.getStar(), requestDto.getContent(), order);
        reviewRepository.save(review);
        return new ReviewResponseDto(review);
    }

    /**
     * 존재하는 주문인지, 리뷰를 남기려는 사용자의 주문이 맞는지 확인하는 메소드
     */
    private Order getUserOrder(Long orderId, User user) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문입니다."));
        if (!order.getUser().getLoginId().equals(user.getLoginId())) {
            throw new IllegalArgumentException("해당 유저의 주문이 아닙니다.");
        }
        return order;
    }
}
