package com.sparta.easydelivery.domain.product.data.Sandwich;

public enum Sandwich15 {
    SpicyBbq (1L, "스파이시 바비큐", 7500L, "부드러운 풀드포크에 매콤함 맛을 더했다!", 10),
    SpicyShrimp (2L, "스파이시 쉬림프", 7900L, "탱글한 쉬림프에 이국적인 시즈닝을 더해 색다른 매콤함을 만나보세요!", 10),
    SpicyItalian (3L, "스파이시 이탈리안", 6900L, "페퍼로니 & 살라미가 입안 가득 페퍼로니의 부드러운 매콤함을 만나보세요!", 10),
    SteakNCheese (4L, "스테이크 & 치즈", 7900L, "육즙이 쫙~풍부한 비프 스테이크의 풍미가 입안 한가득", 10),
    ChickenBaconAvocado (5L, "치킨 베이컨 아보카도", 7900L, "담백하게 닭 가슴살로 만든 치킴 슬라이스와 베이컨, 부드러운 아보카도의 만남", 10),
    RoastChicken (6L, "로스트치킨", 7300L, "오븐에 구워 담백한 저칼로리 닭가슴살의 건강한 풍미", 10),
    RotisserieBarbecueChicken (7L, "로티세리 바비큐 치킨", 7300L, "촉촉한 바비큐 치킨의 풍미가득, 손으로 찢어 더욱 부드러운 치킨의 혁명", 10),
    Kbbq (8L, "k-비비큐", 7300L, "써브웨이의 코리안 스타일 샌드위치! 마늘, 간장 그리고 은은한 붚맛까지!", 10),
    PulledPorkBarbecue (9L, "풀드 포크 바비큐", 7200L, "미국 스타일의 풀드 포크 바비큐가 가득 들어간 샌드위치", 10),
    SubwayClub (10L, "써브웨이 클럽", 7100L, "고소한 베이컨, 담백한 치킨 슬라이스에 햄까지 더해 완벽해진 조황를 즐겨보세요!", 10),
    chickenTeriyaki (11L, "치킨 데리야끼", 7000L, "담백한 스트립에 달콤짭쪼름한 써브웨이 특제 데리야끼 소스와의 환상적인 만남", 10),
    ItalianBMT (12L, "이탈리안 비엠티", 6900L, "페퍼로니, 살라미 그리고 햄이 만들어내는 최상의 조화! 전세계가 사랑하는 써브웨이의 베스트셀러!", 10),
    BLT (13L, "비엘티", 6600L, "오리지널 아메리칸 스타일 베이컨의 풍미와 바삭함 그대로~", 10),
    ChckenSlice (14L, "치킨 슬라이스", 6500L, "달가슴살로 만든 치킨 슬라이스로 즐기는 담백한 맛!", 10),
    Tuna (15L, "참치", 5800L, "남녀노소 누구나 좋아하는 담백한 참치와 고소한 마요네즈의 완벽한 조화", 10),
    Ham (16L, "햄", 5800L, "풍부한 햄이 만들어내는 담백함을 입 안 가득 즐겨보세요!", 10),
    EggMayo (17L, "에그마요", 5500L, "부드러운 달걀과 고소한 마요네즈가 만나 더 부드러운 스테디셀러", 10),
    VeggieDelite (18L, "베지", 4900L, "갓 구운 빵과 신선한 8가지 야채로 즐기는 깔끔한 한끼", 10);

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

    Sandwich15(Long id, String name, Long price, String productDetails, Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productDetails = productDetails;
        this.stock = stock;
    }
}
