package com.sparta.easydelivery.domain.product.data.Sandwich;

public enum Bread {
    HoneyOat (1L, "허니오트", 0L,"고소한 위트 빵에 오트밀 가루를 묻혀 고소함과 식감이 두배로"),
    HeatryItalian (2L, "하티",  0L,"부드러운 화이트빵에 옥수수가루를 묻혀 겉은 바삭하고 고소하며 속은 부드럽게"),
    Wheat (3L, "위트", 0L,"여러 가지 곡물로 만들어 건강하고 고소한 맛의 곡물빵"),
    ParmesanOregano (4L, "파마산오레가노", 0L,"부드러운 화이트빵에 파마산 오레가노 시즈닝을 묻혀 허브향 가득"),
    White (5L, "화이트", 0L,"가장 클래식한 빵으로 부드러운 식감이 매력포인트"),
    FlatBread (6L, "플랫브레드", 0L,"이름처럼 납작 모양에 피자도우처럼 쫀드한 맛이 일품");

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

    Bread(Long id, String name, Long price, String productDetails) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productDetails = productDetails;
    }
}

