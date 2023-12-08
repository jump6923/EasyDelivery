package com.sparta.easydelivery.sale.service;

import com.sparta.easydelivery.sale.dto.CategorySaleResponseDto;
import com.sparta.easydelivery.sale.dto.ProductSaleResponseDto;
import com.sparta.easydelivery.sale.dto.ProductSaleListResponseDto;
import com.sparta.easydelivery.sale.dto.TotalSaleResponseDto;
import com.sparta.easydelivery.order.entity.Order;
import com.sparta.easydelivery.order.entity.OrderStatusEnum;
import com.sparta.easydelivery.order.repository.OrderRepository;
import com.sparta.easydelivery.order.service.OrderProductService;
import com.sparta.easydelivery.product.entity.Product;
import com.sparta.easydelivery.product.repository.ProductRepository;
import com.sparta.easydelivery.user.entity.User;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaleService {

    private final OrderProductService orderProductService;

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    public TotalSaleResponseDto getTotalSales() {
        Long totalSales = 0L;
        List<Order> orderList = orderRepository.findAllByStatus(OrderStatusEnum.COMPLETION);
        for(Order order : orderList){
            totalSales += order.getTotalPrice();
        }
        return new TotalSaleResponseDto(totalSales);
    }

    public CategorySaleResponseDto getCategorySales() {
        CategorySaleResponseDto responseDto = new CategorySaleResponseDto();
        List<String> categoryList = makeCategoryList();
        for(String categoryName : categoryList){
            Long sales = orderProductService.getSaleByCategory(categoryName);
            responseDto.setCategorySaleMap(categoryName, sales);
        }
        return responseDto;
    }

    public ProductSaleListResponseDto getProductSales() {
        ProductSaleListResponseDto responseDto = new ProductSaleListResponseDto();
        List<Product> products = productRepository.findAll();
        for(Product product : products){
            Long sales = orderProductService.getSaleByProduct(product);
            responseDto.productSaleList(new ProductSaleResponseDto(product.getId(), product.getName(), sales));
        }
        return responseDto;
    }

    private List<String> makeCategoryList(){
        List<String> categoryList = new ArrayList<>();
        categoryList.add("bread");
        categoryList.add("cheese");
        categoryList.add("salad");
        categoryList.add("sandwich15");
        categoryList.add("sandwich30");
        categoryList.add("beverage");
        categoryList.add("side");
        categoryList.add("wrap");
        categoryList.add("sauce");
        categoryList.add("veggie");
       return categoryList;
    }

}