package com.sparta.easydelivery.product.entity;

import com.sparta.easydelivery.common.TimeStamp;
import com.sparta.easydelivery.product.dto.ProductRequestDto;
import com.sparta.easydelivery.product.dto.ProductUpdateRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private String productDetails;

    public Product(ProductRequestDto requestDto) {
        this.category = requestDto.getCategory();
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.productDetails = requestDto.getProductDetails();
    }

    public void update(ProductUpdateRequestDto requestDto) {
        this.category = requestDto.getCategory();
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.productDetails = requestDto.getProductDetails();
    }
}
