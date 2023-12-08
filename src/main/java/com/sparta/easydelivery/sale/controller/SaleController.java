package com.sparta.easydelivery.sale.controller;

import com.sparta.easydelivery.sale.dto.CategorySaleResponseDto;
import com.sparta.easydelivery.sale.dto.ProductSaleListResponseDto;
import com.sparta.easydelivery.sale.dto.TotalSaleResponseDto;
import com.sparta.easydelivery.sale.service.SaleService;
import com.sparta.easydelivery.user.implement.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<TotalSaleResponseDto> getTotalSales(
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        TotalSaleResponseDto responseDto = saleService.getTotalSales(userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/category") // 카테고리별 매출
    public ResponseEntity<CategorySaleResponseDto> getCategorySales(
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        CategorySaleResponseDto responseDto = saleService.getCategorySales(userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/product") // 상품별
    public ResponseEntity<ProductSaleListResponseDto> getProductSales(
        @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        ProductSaleListResponseDto responseDto = saleService.getProductSales(userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
