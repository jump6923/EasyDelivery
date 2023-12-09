package com.sparta.easydelivery.domain.review.repository;

import com.sparta.easydelivery.domain.order.entity.Order;
import com.sparta.easydelivery.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Boolean existsByOrder(Order order);
}
