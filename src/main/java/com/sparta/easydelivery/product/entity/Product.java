package com.sparta.easydelivery.product.entity;

import com.sparta.easydelivery.product.dto.ProductRequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;

@Entity
@Getter
@Table(name = "product")
@NoArgsConstructor
public class Product {
    // OrderProduct 와 1대 N 관계
    @OneToMany(targetEntity = OrderProduct.class, mappedBy = "product",cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<OrderProduct> orderProductList = new ArrayList<>();

    // Cart 와 1대 N 관계
    @OneToMany(targetEntity = Cart.class, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Cart> cartList = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Long productPrice;

    @Column(nullable = false)
    private String productDetails;

    public Product(ProductRequestDto requestDto, Admin admin) {
        this.category = requestDto.getCategory();
        this.productName = requestDto.getProductName();
        this.productPrice = requestDto.getProductPrice();
        this.productDetails = requestDto.getProductDetails();
    }

    public void update(ProductRequestDto requestDto) {
        this.category = requestDto.getCategory();
        this.productName = requestDto.getProductName();
        this.productPrice = requestDto.getProductPrice();
        this.productDetails = requestDto.getProductDetails();
    }
}
