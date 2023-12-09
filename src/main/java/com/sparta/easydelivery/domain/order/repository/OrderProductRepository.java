package com.sparta.easydelivery.domain.order.repository;

import com.sparta.easydelivery.domain.order.entity.OrderProduct;
import com.sparta.easydelivery.domain.product.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    List<OrderProduct> findAllByProductCategory(String categoryName);

    List<OrderProduct> findAllByProduct(Product product);
}
