package com.sparta.easydelivery.order.repository;

import com.sparta.easydelivery.order.entity.Order;
import com.sparta.easydelivery.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUser(User user);
}
