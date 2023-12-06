package com.sparta.easydelivery.cart.controller;

import com.sparta.easydelivery.cart.dto.CartListResponseDto;
import com.sparta.easydelivery.cart.dto.CartRequestDto;
import com.sparta.easydelivery.cart.dto.CartResponseDto;
import com.sparta.easydelivery.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    @PostMapping("")
    public ResponseEntity<CartResponseDto> addCart(
        @RequestBody CartRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        CartResponseDto responseDto = cartService.addCart(requestDto, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("")
    public ResponseEntity<CartListResponseDto> getCarts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        CartListResponseDto responseDto = cartService.getCarts(userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

}
