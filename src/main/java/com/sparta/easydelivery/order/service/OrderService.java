package com.sparta.easydelivery.order.service;

import com.sparta.easydelivery.cart.entity.Cart;
import com.sparta.easydelivery.cart.service.CartService;
import com.sparta.easydelivery.common.exception.UnauthorizedUserException;
import com.sparta.easydelivery.order.dto.OrderDetailResponseDto;
import com.sparta.easydelivery.order.dto.OrderMapResponseDto;
import com.sparta.easydelivery.order.dto.OrderRequestDto;
import com.sparta.easydelivery.order.dto.OrderResponseDto;
import com.sparta.easydelivery.order.dto.OrderStatusRequestDto;
import com.sparta.easydelivery.order.entity.Order;
import com.sparta.easydelivery.order.entity.OrderStatusEnum;
import com.sparta.easydelivery.order.exception.EmptyCartException;
import com.sparta.easydelivery.order.exception.NotFoundOrderException;
import com.sparta.easydelivery.order.repository.OrderRepository;
import com.sparta.easydelivery.user.entity.User;
import com.sparta.easydelivery.user.entity.UserRoleEnum;
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
            throw new EmptyCartException();
        }
        order.setTotalPrice(cartList); // 장바구니 상품 가격*수량의 합계를 총가격에 저장
        Order saveOrder = orderRepository.save(order);
        orderProductService.ConvertCartToOrderProduct(cartList, order); //장바구니에 있는 상품을 ProductOrder에 옮겨 저장
        cartService.clearCart(user); // 장바구니 비우기


        return new OrderResponseDto(saveOrder);
    }

    public OrderMapResponseDto getOrders(User user){
        OrderMapResponseDto orderMap = new OrderMapResponseDto();

        if(user.getRole() == UserRoleEnum.USER) {  // 사용자가 조회한 경우
            for(OrderStatusEnum status : OrderStatusEnum.values()){
                orderMap.setOrderToMap(status, orderRepository.findAllByUserAndStatus(user, status).stream()
                    .map(OrderResponseDto::new).toList());
            }
        }
        else{ // 관리자가 조회한 경우
            for(OrderStatusEnum status : OrderStatusEnum.values()){
                orderMap.setOrderToMap(status, orderRepository.findAllByStatus(status).stream()
                    .map(OrderResponseDto::new).toList());
            }
        }

        return orderMap;
    }

    public OrderDetailResponseDto getOrder(Long orderId, User user){
        Order order = getOrderEntity(orderId, user);
        return new OrderDetailResponseDto(order);
    }

    @Transactional
    public OrderResponseDto updateOrderStatus(Long orderId, OrderStatusRequestDto requestDto, User user){
        Order order = getOrderEntity(orderId, user);
        order.setStatus(requestDto.getOrderStatusEnum());
        return new OrderResponseDto(order);
    }

    public void deleteOrder(Long orderId, User user){
        Order order = getOrderEntity(orderId, user);
        orderRepository.delete(order);
    }

    public Order getOrderEntity(Long orderId, User user) {
        Order order = orderRepository.findById(orderId).orElseThrow(NotFoundOrderException::new);
        if(user.getRole() == UserRoleEnum.USER && !order.getUser().getUsername().equals(user.getUsername())){
            throw new UnauthorizedUserException();
        }
        return order;
    }

}
