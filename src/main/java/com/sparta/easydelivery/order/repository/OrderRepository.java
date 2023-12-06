package com.sparta.easydelivery.order.repository;

import com.sparta.easydelivery.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
