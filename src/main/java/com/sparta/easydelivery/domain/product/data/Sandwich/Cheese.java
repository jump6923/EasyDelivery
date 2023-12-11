package com.sparta.easydelivery.domain.product.data.Sandwich;

public enum Cheese {
    AmericanCheese (1L, "아메리칸 치즈", 0L,"신선한 아메리칸 치즈", 10),
    ShreddedCheese (2L,"슈레드 치즈", 0L,"신선한 슈레드 치즈", 10),
    MozzarellaCheese (3L, "모차렐라 치즈", 0L,"신선한 모차렐라 치즈", 10);

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

    Cheese(Long id, String name, Long price, String productDetails, Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productDetails = productDetails;
        this.stock = stock;
    }
}
