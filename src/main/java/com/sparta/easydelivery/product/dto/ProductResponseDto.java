package com.sparta.easydelivery.product.dto;

import com.sparta.easydelivery.product.entity.Product;
public record ProductResponseDto (
    Long id,
    String category,
    String name,
    Long price,
    String productDetails
) {
    public ProductResponseDto(Product saveProduct) {
        this (
            saveProduct.getId(),
            saveProduct.getCategory(),
            saveProduct.getName(),
            saveProduct.getPrice(),
            saveProduct.getProductDetails()
        );
    }
}
