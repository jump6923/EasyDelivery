package com.sparta.easydelivery.product.data;

public enum Veggie {
    Lettuce (1L, "양상추", 0L,"신선한 양상추"),
    Tomatoes (2L, "토마토",  0L,"신선한 토마토"),
    Cucumbers (3L, "오이", 0L,"신선한 오이"),
    Peppers (4L, "피망", 0L,"신선한 피망"),
    RedOnions (5L, "양파", 0L,"신선한 양파"),
    Pickles (6L, "피클", 0L,"신선한 피클"),
    Olives (7L, "올리브", 0L,"신선한 올리브"),
    Jalapenos (8L, "할라피뇨", 0L,"신선한 할라피뇨"),
    Avocado (9L, "아보카도", 0L,"신선한 아보카도");

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

    Veggie(Long id, String name, Long price, String productDetails) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productDetails = productDetails;
    }
}
