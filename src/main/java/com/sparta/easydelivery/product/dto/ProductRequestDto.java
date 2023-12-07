package com.sparta.easydelivery.product.dto;

import lombok.Getter;

@Getter
public class ProductRequestDto {

    private String category;

    private String name;

    private Long price;

    private String productDetails;
}
