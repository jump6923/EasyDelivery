package com.sparta.easydelivery.order.controller;

import com.sparta.easydelivery.order.dto.OrderRequestDto;
import com.sparta.easydelivery.order.dto.OrderResponseDto;
import com.sparta.easydelivery.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public ResponseEntity<OrderResponseDto> createOrder(
        @RequestBody OrderRequestDto orderRequestDto,
        @AuthenticationPrincipal UserDetils userDetils
    ){
        OrderResponseDto responseDto = orderService.createOrder(orderRequestDto, userDetils.getUser());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
