package com.sparta.easydelivery.cart.controller;

import com.sparta.easydelivery.cart.dto.CartListResponseDto;
import com.sparta.easydelivery.cart.dto.CartRequestDto;
import com.sparta.easydelivery.cart.dto.CartResponseDto;
import com.sparta.easydelivery.cart.dto.CartUpdateRequestDto;
import com.sparta.easydelivery.cart.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    @PostMapping("")
    public ResponseEntity<CartResponseDto> addCart(
        @Valid @RequestBody CartRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        CartResponseDto responseDto = cartService.addCart(requestDto, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("")
    public ResponseEntity<CartListResponseDto> getCarts(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        CartListResponseDto responseDto = cartService.getCarts(userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> deleteCart(
        @PathVariable Long cartId,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        cartService.deleteCart(cartId, userDetails.getUser());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity<?> clearCart(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        cartService.clearCart(userDetails.getUser());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{cartId}")
    public ResponseEntity<CartResponseDto> updateQuantity(
        @PathVariable Long cartId,
        @Valid @RequestBody CartUpdateRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        CartResponseDto responseDto = cartService
            .updateQuantity(cartId, requestDto.getQuantity(), userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

}
