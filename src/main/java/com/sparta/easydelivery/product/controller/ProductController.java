package com.sparta.easydelivery.product.controller;

import com.sparta.easydelivery.product.dto.ProductRequestDto;
import com.sparta.easydelivery.product.dto.ProductResponseDto;
import com.sparta.easydelivery.product.productservice.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    // 상품 목록 조회
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts() {
        List<ProductResponseDto> responseDto = productService.getProducts();
        return ResponseEntity.ok(responseDto);
    }

    // 상품 상세 조회
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> getProduct(
        @PathVariable Long productId) {
        ProductResponseDto responseDto = productService.getProduct(productId);
        return ResponseEntity.ok(responseDto);
    }

    // 수정 기능 - admin만 수정 가능하도록 구현
    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(
        @PathVariable Long productId,
        @Valid @RequestBody ProductRequestDto requestDto
        // @AuthenticationPrincipal
    ) {
        ProductResponseDto responseDto = productService.updateProduct(productId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 삭제 기능 - admin만 삭제 가능하도록 구현
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteProduct(
        @PathVariable Long productId
        // @AuthenticationPrincipal
    ) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
