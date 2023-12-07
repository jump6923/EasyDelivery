package com.sparta.easydelivery.order.service;

import com.sparta.easydelivery.cart.entity.Cart;
import com.sparta.easydelivery.order.entity.Order;
import com.sparta.easydelivery.order.entity.OrderProduct;
import com.sparta.easydelivery.order.repository.OrderProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProductService {

    private final OrderProductRepository orderProductRepository;

    public void ConvertCartToOrderProduct(List<Cart> cartList, Order order){
        List<OrderProduct> orderProducts = cartList.stream()
            .map(cart -> new OrderProduct(cart, order)).toList();
        orderProductRepository.saveAll(orderProducts);
    }
}
