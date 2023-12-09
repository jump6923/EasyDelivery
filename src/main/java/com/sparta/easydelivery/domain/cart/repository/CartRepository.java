package com.sparta.easydelivery.domain.cart.repository;

import com.sparta.easydelivery.domain.cart.entity.Cart;
import com.sparta.easydelivery.domain.product.entity.Product;
import com.sparta.easydelivery.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Boolean existsByUserAndProduct(User user, Product product);

    /**
     * deleteAllByUser 를 사용하게 되면 cart의 개수만큼 쿼리가 나가서 jpql 을 사용
     */
    @Modifying // select 외의 쿼리를 사용하기 위해서 필요함
    @Query(value = "delete from Cart c where c.user = :user")
    void clearCartByUser(User user);

    /**
     * N+1 문제가 발생하여 fetch join 사용해서 해결
     */
    @Query(value = "select c from Cart c join fetch c.product p where c.user = :user")
    List<Cart> findAllFetchByUser(User user);
}
