package com.sparta.easydelivery.review.repository;

import com.sparta.easydelivery.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Boolean existsByOrder(Order order);
}
