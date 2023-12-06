package com.sparta.easydelivery.order.controller;

import com.sparta.easydelivery.order.dto.OrderListResponseDto;
import com.sparta.easydelivery.order.dto.OrderRequestDto;
import com.sparta.easydelivery.order.dto.OrderResponseDto;
import com.sparta.easydelivery.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
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
        @AuthenticationPrincipal UserDetils userDetils
    ){
        OrderResponseDto responseDto = orderService.createOrder(orderRequestDto, userDetils.getUser());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<OrderListResponseDto> getOrders(
        @AuthenticationPrincipal UserDetails userDetails
    ){
        OrderListResponseDto responseDto = orderService.getOrders(userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrder(
        @PathVariable Long orderId,
        @AuthenticationPrincipal UserDetails userDetails
    ){
        OrderResponseDto responseDto = orderService.getOrder(orderId, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }


}
