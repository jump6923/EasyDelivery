package com.sparta.easydelivery.domain.sale.controller;

import com.sparta.easydelivery.domain.sale.dto.CategorySaleResponseDto;
import com.sparta.easydelivery.domain.sale.dto.PeriodSaleResponseDto;
import com.sparta.easydelivery.domain.sale.dto.ProductSaleListResponseDto;
import com.sparta.easydelivery.domain.sale.dto.TotalSaleResponseDto;
import com.sparta.easydelivery.domain.sale.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    /**
     * 관리자가 주문 매출을 조회 가능
     */

    @GetMapping("/total") // 총 매출
    public ResponseEntity<TotalSaleResponseDto> getTotalSales(){
        TotalSaleResponseDto responseDto = saleService.getTotalSales();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/category") // 카테고리별 매출
    public ResponseEntity<CategorySaleResponseDto> getCategorySales(){
        CategorySaleResponseDto responseDto = saleService.getCategorySales();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/product") // 상품별
    public ResponseEntity<ProductSaleListResponseDto> getProductSales(){
        ProductSaleListResponseDto responseDto = saleService.getProductSales();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/period") // 기간별
    public ResponseEntity<PeriodSaleResponseDto> getPeriodSales(String sort) {
        PeriodSaleResponseDto responseDto = saleService.getPeriodSales(sort);

        return ResponseEntity.ok(responseDto);
    }
}
