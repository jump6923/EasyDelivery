package com.sparta.easydelivery.order.repository;

import com.sparta.easydelivery.order.entity.Order;
import com.sparta.easydelivery.order.entity.OrderProduct;
import com.sparta.easydelivery.product.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    List<OrderProduct> findAllByProductCategory(String categoryName);

    List<OrderProduct> findAllByProduct(Product product);
}
