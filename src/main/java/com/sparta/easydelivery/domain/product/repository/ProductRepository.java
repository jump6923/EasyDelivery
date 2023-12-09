package com.sparta.easydelivery.domain.product.repository;

import com.sparta.easydelivery.domain.product.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

        List<Product>findAllByOrderByCategory();
}
