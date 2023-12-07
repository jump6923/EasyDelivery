package com.sparta.easydelivery.product.data.Side;

public enum Beverage {
    Cocacola (1L, "코카콜라", 2000L,""),
    CocacolaZero (3L, "코카콜라 제로", 2000L,""),
    SpriteZero (4L, "스프라이트 제로", 2000L,""),
    DoctorPepper  (5L, "닥터페퍼", 2000L,"");

    private final Long id;
    private final String name;
    private final Long price;
    private final String productDetails;

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

    Beverage(Long id, String name, Long price, String productDetails) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productDetails = productDetails;
    }
}
