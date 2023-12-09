package com.sparta.easydelivery.domain.product.data.Side;

public enum Side {
    CookieBox (1L, "쿠키박스 (12개입)", 14200L,"쿠키 12개가 랜덤으로 제공되며, 제공된 쿠키의 영양성분은 개별 쿠키 영양성분표를 참고하시기 바랍니다."),
    ChocolateChip (2L, "초코칩", 1300L,"알알이 가득한 초코의 가장 클래식한 달콤함"),
    DoubleChocolateChip  (3L, "더블 초코칩", 1300L,"부드러운 화이트초콜릿과 다크 초콜릿의 절묘한 조화로 더욱 풍부한 달콤함"),
    OatmealRaisin  (4L, "오트밀 레이즌", 1300L,"건포도와 귀리에 살짝 더해진 계피향의 환상적인 조화"),
    WhiteChocoMacadamia   (5L, "화이트 초코 마카다미아", 1300L,"고소함 가득한 마카다미아와 달콤한 화이트 초콜릿의 환상 궁합"),
    ThreeCookies  (6L, "라즈베리 치즈케익", 1300L,"부드럽고 풍부한 치즈와 새콤달콤 라즈베리의 달콤한 만남"),
    RaspberryCheeseCake   (7L, "쿠키 3개", 3700L,"쿠키 3개가 랜덤으로 제공되며, 제공된 쿠키의 영양성분은 개별 쿠키 영양성분표를 참고하시기 바랍니다."),
    CookiePack  (8L, "쿠키팩 (6개입)", 7200L,"쿠키 6개가 랜덤으로 제공되며, 제공된 쿠키의 영양성분은 개별 쿠키 영양성분표를 참고하시기 바랍니다.");

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

    Side(Long id, String name, Long price, String productDetails) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productDetails = productDetails;
    }
}
