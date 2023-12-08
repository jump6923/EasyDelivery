package com.sparta.easydelivery.sale.dto;

import lombok.Getter;

@Getter
public class TotalSaleResponseDto {
    private final Long totalSales;

    public TotalSaleResponseDto(Long totalSales) {
        this.totalSales = totalSales;
    }
}
