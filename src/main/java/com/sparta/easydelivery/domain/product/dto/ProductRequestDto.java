package com.sparta.easydelivery.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductRequestDto {

    private String category;

    private String name;

    private Long price;

    private String productDetails;

    private int stock;
}
