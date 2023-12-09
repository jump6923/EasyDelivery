package com.sparta.easydelivery.domain.product.data;

public enum Sauce {
    Ranch(1L, "랜치", 0L,"고소한 마요네즈와 레몬즙의 만남! 고소함이 두배!"),
    SweetOnion(2L, "스위트 어니언", 0L,"써브웨이만의 특제 레시피로 만든 달콤한 양파소스"),
    Mayonnaise(3L, "마요네즈", 0L,"말이 필요없는 고소함의 대명사, 마요네즈 소스"),
    SweetChilli(4L, "스위트 칠리", 0L,"매콤한 칠리에 더해진 기분 좋은 달콤함!"),
    SmokeBBQ(5L, "스모크 바베큐", 0L,"스모크 향과 달콤한 바비큐의 완벽한 조화"),
    HoyChilli(6L, "핫 칠리", 0L,"진짜 매운맛을 보고 싶다면? 써브웨이의 핫 칠리!"),
    HoneyMustard(7L, "허니 머스터드", 0L,"겨자씨가 아낌없이 들어간 달콤한 머스터드 소스"),
    SouthwestChipotle(8L, "사우스웨스트 치폴레", 0L,"핫 칠리와 고소한 마요네즈가 만난 이국적인 매콤한 맛"),
    Horseradish(9L, "홀스래디쉬", 0L,"고소한 마요네즈와 고추냉이의 이색적인 만남! 매니아층을 사로잡은 매력 No.1 소스"),
    YellowMustard(10L, "머스타드", 0L,"겨자씨로 만든 오리지날 머스터드 소스"),
    OliveOil(11L, "올리브 오일", 0L,"담백하고 깔끔하게 즐기고 싶다면 주저하지 말고 올리브오일"),
    RedWineVinaigrette(12L, "레드 와인 식초", 0L, "레드와인을 발효시켜 풍미가 가득한 식초"),
    Salt(13L, "소금",0L, " "),
    BlackPepper(14L, "후추", 0L," ");


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

    Sauce(Long id, String name, Long price, String productDetails) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productDetails = productDetails;
    }
}
