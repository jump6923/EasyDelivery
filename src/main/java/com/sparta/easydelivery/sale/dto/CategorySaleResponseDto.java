package com.sparta.easydelivery.sale.dto;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public class CategorySaleResponseDto {

    Map<String, Long> categorySaleMap = new HashMap<>();

    public void setCategorySaleMap(String categoryName, Long sales){
        categorySaleMap.put(categoryName, sales);
    }

}
