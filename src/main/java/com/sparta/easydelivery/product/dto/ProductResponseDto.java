package com.sparta.easydelivery.product.dto;

import com.sparta.easydelivery.product.entity.Product;
public record ProductResponseDto (
    Long id,
    String category,
    String productName,
    Long productPrice,
    String productDetails
) {
    public ProductResponseDto(Product saveProduct) {
        this (
            saveProduct.getId(),
            saveProduct.getCategory(),
            saveProduct.getProductName(),
            saveProduct.getProductPrice(),
            saveProduct.getProductDetails()
        );
    }
}
