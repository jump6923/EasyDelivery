package com.sparta.easydelivery.order.repository;

import com.sparta.easydelivery.order.entity.Order;
import com.sparta.easydelivery.order.entity.OrderStatusEnum;
import com.sparta.easydelivery.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserAndStatus(User user, OrderStatusEnum orderStatusEnum);

    List<Order> findAllByStatus(OrderStatusEnum orderStatusEnum);
}
