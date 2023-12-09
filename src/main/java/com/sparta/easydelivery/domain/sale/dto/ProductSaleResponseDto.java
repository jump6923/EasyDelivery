package com.sparta.easydelivery.domain.sale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductSaleResponseDto {
    Long id;
    String name;
    Long sales;
}
