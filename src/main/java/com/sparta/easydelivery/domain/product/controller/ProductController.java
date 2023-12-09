package com.sparta.easydelivery.domain.product.controller;

import com.sparta.easydelivery.domain.product.service.ProductService;
import com.sparta.easydelivery.domain.product.dto.ProductRequestDto;
import com.sparta.easydelivery.domain.product.dto.ProductResponseDto;
import com.sparta.easydelivery.domain.product.dto.ProductUpdateRequestDto;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // 상품 추가
    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto requestDto) {
        ProductResponseDto responseDto = productService.addProduct(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 수정 기능 - admin만 수정 가능하도록 구현
    @PatchMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(
        @PathVariable Long productId,
        @Valid @RequestBody ProductUpdateRequestDto requestDto
    ) {
        ProductResponseDto responseDto = productService.updateProduct(productId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    // 삭제 기능 - admin만 삭제 가능하도록 구현
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(
        @PathVariable Long productId
    ) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
