package com.sparta.easydelivery.order.repository;

import com.sparta.easydelivery.order.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

}
