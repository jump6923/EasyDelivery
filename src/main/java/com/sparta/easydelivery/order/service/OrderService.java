package com.sparta.easydelivery.order.service;

import com.sparta.easydelivery.cart.entity.Cart;
import com.sparta.easydelivery.cart.service.CartService;
import com.sparta.easydelivery.order.dto.OrderRequestDto;
import com.sparta.easydelivery.order.dto.OrderResponseDto;
import com.sparta.easydelivery.order.entity.Order;
import com.sparta.easydelivery.order.exception.OrderNothingException;
import com.sparta.easydelivery.order.repository.OrderRepository;
import com.sparta.easydelivery.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderProductService orderProductService;

    private final CartService cartService;

    public OrderResponseDto createOrder(OrderRequestDto requestDto, User user){
        // dto -> entity
        Order order = new Order(requestDto, user);
        List<Cart> cartList = cartService.getCartList(user); //사용자의 장바구니
        if(cartList.isEmpty()){
            throw new OrderNothingException("주문 할 상품이 없습니다.");
        }
        orderProductService.ConvertCartToOrderProduct(cartList, order); //장바구니에 있는 상품을 ProductOrder에 옮겨 저장
        order.setTotalPrice(cartList); // 장바구니 상품 가격*수량의 합계를 총가격에 저장
        Order saveOrder = orderRepository.save(order);

        return new OrderResponseDto(saveOrder);
    }
}
