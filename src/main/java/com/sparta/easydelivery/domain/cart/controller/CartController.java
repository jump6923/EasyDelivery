package com.sparta.easydelivery.domain.cart.controller;

import com.sparta.easydelivery.domain.cart.dto.CartListResponseDto;
import com.sparta.easydelivery.domain.cart.dto.CartRequestDto;
import com.sparta.easydelivery.domain.cart.dto.CartResponseDto;
import com.sparta.easydelivery.domain.cart.dto.CartUpdateRequestDto;
import com.sparta.easydelivery.domain.cart.service.CartService;
import com.sparta.easydelivery.domain.user.implement.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
