package com.sparta.easydelivery.sale.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ProductSaleListResponseDto {
    List<ProductSaleResponseDto> productSaleList = new ArrayList<>();

    public void productSaleList(ProductSaleResponseDto responseDto){
        productSaleList.add(responseDto);
    }
}
