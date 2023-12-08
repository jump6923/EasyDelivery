package com.sparta.easydelivery.order.controller;

import com.sparta.easydelivery.order.dto.OrderDetailResponseDto;
import com.sparta.easydelivery.order.dto.OrderMapResponseDto;
import com.sparta.easydelivery.order.dto.OrderRequestDto;
import com.sparta.easydelivery.order.dto.OrderResponseDto;
import com.sparta.easydelivery.order.dto.OrderStatusRequestDto;
import com.sparta.easydelivery.order.service.OrderService;
import com.sparta.easydelivery.user.implement.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(
        @RequestBody @Valid OrderRequestDto orderRequestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        OrderResponseDto responseDto = orderService.createOrder(orderRequestDto, userDetails.getUser());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<OrderMapResponseDto> getOrders(
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        OrderMapResponseDto responseDto = orderService.getOrders(userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetailResponseDto> getOrder(
        @PathVariable Long orderId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        OrderDetailResponseDto responseDto = orderService.getOrder(orderId, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> updateOrderStatus(
        @PathVariable Long orderId,
        @RequestBody @Valid OrderStatusRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        OrderResponseDto responseDto = orderService.updateOrderStatus(orderId, requestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(
        @PathVariable Long orderId,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        orderService.deleteOrder(orderId, userDetails.getUser());
        return ResponseEntity.noContent().build();
    }
}
