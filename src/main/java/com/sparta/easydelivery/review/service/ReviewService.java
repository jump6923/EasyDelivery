package com.sparta.easydelivery.review.service;

import com.sparta.easydelivery.common.exception.UnauthorizedUserException;
import com.sparta.easydelivery.order.entity.Order;
import com.sparta.easydelivery.order.entity.OrderStatusEnum;
import com.sparta.easydelivery.order.service.OrderService;
import com.sparta.easydelivery.review.dto.ReviewListResponseDto;
import com.sparta.easydelivery.review.dto.ReviewRequestDto;
import com.sparta.easydelivery.review.dto.ReviewResponseDto;
import com.sparta.easydelivery.review.dto.ReviewUpdateRequestDto;
import com.sparta.easydelivery.review.entity.Review;
import com.sparta.easydelivery.review.exception.DuplicatedReviewException;
import com.sparta.easydelivery.review.exception.NotCompletedOrderException;
import com.sparta.easydelivery.review.exception.NotFoundReviewException;
import com.sparta.easydelivery.review.repository.ReviewRepository;
import com.sparta.easydelivery.user.entity.User;
import com.sparta.easydelivery.user.entity.UserRoleEnum;
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
    private final OrderService orderService;

    public ReviewResponseDto createReview(ReviewRequestDto requestDto, User user) {
        Order order = orderService.getOrderEntity(requestDto.getOrderId(), user);
        if(order.getStatus()!= OrderStatusEnum.COMPLETION){
            throw new NotCompletedOrderException();
        }
        if (reviewRepository.existsByOrder(order)) {
            throw new DuplicatedReviewException();
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
        if (user.getRole() == UserRoleEnum.ADMIN) {
            Review review = reviewRepository.findById(reviewId)
                .orElseThrow(NotFoundReviewException::new);
            reviewRepository.delete(review);
            return;
        }
        Review review = getUserReview(reviewId, user);
        reviewRepository.delete(review);
    }

    private Review getUserReview(Long reviewId, User user) {
        Review review = reviewRepository.findById(reviewId)
            .orElseThrow(NotFoundReviewException::new);
        if (!review.getUser().getUsername().equals(user.getUsername())) {
            throw new UnauthorizedUserException();
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
