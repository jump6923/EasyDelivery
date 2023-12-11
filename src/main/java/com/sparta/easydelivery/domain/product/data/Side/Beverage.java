package com.sparta.easydelivery.domain.product.data.Side;

public enum Beverage {
    Cocacola (1L, "코카콜라", 2000L,"", 10),
    CocacolaZero (3L, "코카콜라 제로", 2000L,"", 10),
    SpriteZero (4L, "스프라이트 제로", 2000L,"", 10),
    DoctorPepper  (5L, "닥터페퍼", 2000L,"", 10);

    private final Long id;
    private final String name;
    private final Long price;
    private final String productDetails;

    private final Integer stock;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public Integer getStock() {
        return stock;
    }

    Beverage(Long id, String name, Long price, String productDetails, Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productDetails = productDetails;
        this.stock = stock;
    }
}
