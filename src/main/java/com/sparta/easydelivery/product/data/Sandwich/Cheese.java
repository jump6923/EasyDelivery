package com.sparta.easydelivery.product.data.Sandwich;

public enum Cheese {
    AmericanCheese (1L, "아메리칸 치즈", 0L,"신선한 아메리칸 치즈"),
    ShreddedCheese (2L,"슈레드 치즈", 0L,"신선한 슈레드 치즈"),
    MozzarellaCheese (3L, "모차렐라 치즈", 0L,"신선한 모차렐라 치즈");

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

    Cheese(Long id, String name, Long price, String productDetails) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productDetails = productDetails;
    }
}
