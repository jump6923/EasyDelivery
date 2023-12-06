package com.sparta.easydelivery.product.repository;

import com.sparta.easydelivery.product.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

        Optional<Product> findByProductName(String produstName);

        List<Product>findAllByOrderByProductCategory();
}
