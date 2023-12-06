package com.sparta.easydelivery.cart.repository;

import com.sparta.easydelivery.cart.entity.Cart;
import com.sparta.easydelivery.cart.temp.Product;
import com.sparta.easydelivery.cart.temp.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Boolean existsByUserAndProduct(User user, Product product);

    List<Cart> findAllByUser(User user);
}
