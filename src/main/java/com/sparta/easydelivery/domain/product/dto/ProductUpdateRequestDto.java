package com.sparta.easydelivery.domain.product.dto;

import lombok.Getter;

@Getter
public class ProductUpdateRequestDto {
    private String category;

    private String name;

    private Long price;

    private String productDetails;

    private int stock;

}
