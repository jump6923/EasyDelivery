package com.sparta.easydelivery.product.dto;

import lombok.Getter;

@Getter
public class ProductRequestDto {

    private String category;

    private String productName;

    private Long productPrice;

    private String productDetails;
}
