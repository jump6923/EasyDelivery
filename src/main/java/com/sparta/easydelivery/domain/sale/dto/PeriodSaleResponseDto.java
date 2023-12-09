package com.sparta.easydelivery.domain.sale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PeriodSaleResponseDto {

    private String period;
    private Long price;

}
