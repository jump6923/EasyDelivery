package com.sparta.easydelivery.domain.product.entity;

import com.sparta.easydelivery.common.TimeStamp;
import com.sparta.easydelivery.domain.order.entity.OrderProduct;
import com.sparta.easydelivery.domain.product.dto.ProductRequestDto;
import com.sparta.easydelivery.domain.product.dto.ProductUpdateRequestDto;
import com.sparta.easydelivery.domain.product.exception.NotEnoughStockException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
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

    @Column(nullable = false)
    private int stock;

    private boolean isDeleted = false;

    @OneToMany(mappedBy = "product")
    private List<OrderProduct> orderProduct = new ArrayList<>();

    public Product(ProductRequestDto requestDto) {
        this.category = requestDto.getCategory();
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.productDetails = requestDto.getProductDetails();
        this.stock = requestDto.getStock();
    }

    public Product(String category, String name, Long price, String productDetails, int stock) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.productDetails = productDetails;
        this.stock = stock;
    }

    public void update(ProductUpdateRequestDto requestDto) {
        this.category = requestDto.getCategory();
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.productDetails = requestDto.getProductDetails();
        this.stock = requestDto.getStock();
    }

    public void reduceStock(int quantity) {
        checkStock(quantity);
        this.stock -= quantity;
    }

    public void checkStock(int quantity) {
        if (this.stock - quantity < 0) {
            throw new NotEnoughStockException();
        }
    }

    public void delete() {
        isDeleted = true;
    }
}
