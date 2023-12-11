package com.sparta.easydelivery.domain.product.repository;

import com.sparta.easydelivery.domain.product.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

        List<Product>findAllByOrderByCategory();

        @Query(value = "select p from Product p left join fetch p.orderProduct op")
        List<Product> findAllByFetchJoinOrderProduct();
}
