package com.sparta.easydelivery.cart.repository;

import com.sparta.easydelivery.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
