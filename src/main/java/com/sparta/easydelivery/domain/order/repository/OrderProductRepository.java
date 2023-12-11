package com.sparta.easydelivery.domain.order.repository;

import com.sparta.easydelivery.domain.order.entity.OrderProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    List<OrderProduct> findAllByProductCategory(String categoryName);
}
