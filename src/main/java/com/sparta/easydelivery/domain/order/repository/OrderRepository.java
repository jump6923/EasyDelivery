package com.sparta.easydelivery.domain.order.repository;

import com.sparta.easydelivery.domain.order.entity.Order;
import com.sparta.easydelivery.domain.order.entity.OrderStatusEnum;
import com.sparta.easydelivery.domain.user.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserAndStatus(User user, OrderStatusEnum orderStatusEnum);

    List<Order> findAllByStatus(OrderStatusEnum orderStatusEnum);

    List<Order> findAllByCreatedAtGreaterThan(LocalDateTime period);
}
