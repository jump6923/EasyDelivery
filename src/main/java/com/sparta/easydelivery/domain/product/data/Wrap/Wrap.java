package com.sparta.easydelivery.domain.product.data.Wrap;

public enum Wrap {
    SteakNCheeseAvocadoWrap (1L, "스테이크 & 치즈 아보카도 랩", 6600L,"육즙 가득 스테이크에 프레쉬함을 더해줄 아보카도와 슈레드치즈 그리고 모차렐라치즈까지!최상의 야채와 소스 조합으로 탄생한 스테이크 & 치즈 아보카도 랩!", 10),
    ShrimpEggMayoWrap (2L, "쉬림프 에그마요 랩", 6200L,"고소한 에그마요와 탱글한 통새우의 만남, 부드럽고 담백한 조화를 즐겨보세요!", 10),
    ChickenBaconMiniWrap  (3L, "치킨 베이컨 미니 랩", 3900L,"담백한 치킨, 바삭한 베이컨 비츠가 쫀득한 통밀 랩에 쏘옥! 치킨 베이컨 미니 랩", 10);

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

    Wrap(Long id, String name, Long price, String productDetails, Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productDetails = productDetails;
        this.stock = stock;
    }
}
