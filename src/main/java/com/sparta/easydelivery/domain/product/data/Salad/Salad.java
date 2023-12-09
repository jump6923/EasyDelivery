package com.sparta.easydelivery.domain.product.data.Salad;

public enum Salad {
    NewShrimp(1L, "New 쉬림프", 7500L, "Bigger is Better, 더 커지고 맛있어진 써브웨이 New 쉬림프를 만나보세요."),
    NewSpicyShrimp(2L, "New 스파이시 쉬림프", 7500L, "Bigger is Better, 더 커지고 맛있어진 써브웨이 New 스파이시 쉬림프를 만나보세요."),
    SteakNCheese(3L, "스테이크 & 치즈", 7500L, "육즙이 쫙~풍부한 비프 스테이크의 풍미 가득 스테이크 & 치즈 샐러드!"),
    ChickenBaconAvocado(4L, "치킨 베이컨 아보카도", 7500L, "담백하게 닭가슴살로 만든 치킨 슬라이스와 베이컨, 부드러운 아보카도의 만남"),
    RoastedChicken(5L, "로스트 치킨", 7500L, "오븐에 구워 담백한 저칼로리 닭가슴살의 건강한 풍미"),
    RotisserieBarbecueChicken(6L, "로티세리 바비큐 치킨", 7500L, "촉촉한 바비큐 치킨의 풍미가득, 손으로 찢어 더욱 부드러운 치킨이 샐러드에 쏙!"),
    KBbq(7L, "K-BBQ", 7500L, "써브웨이 최초의 코리안 스타일 샐러드! 마늘, 간장 그리고 은은한 불맛까지!"),
    PulledPorkBarbecue(8L, "풀드 포크 바비큐", 7500L, "훈연한 미국 스타일의 풀드포크 바비큐가 가득 올라간 풍미 가득한 샐러드"),
    SubwayClub(9L, "써브웨이 클럽", 7500L, "고소한 베이컨, 담백한 치킨 슬라이스에 햄까지 더해진 완벽한 앙상블"),
    ChickenTeriyaki(10L, "치킨 데리야끼", 7500L, "담백한 치킨 스트립에 달콤짭쪼름한 써브웨이 특제 데리야끼 소스 토핑으로 더 풍성한 샐러드"),
    SpicyItalian(12L, "스파이시 이탈리안", 7500L, "살라미, 페퍼로니가 입안 한가득! 진짜 이탈리아의 맛 가득한 샐러드"),
    ItalianBMT(13L, "이탈리안 비엠티", 7500L, "페퍼로니, 살라미 그리고 햄이 만들어내는 최상의 조화! 전세계가 사랑하는 써브웨이의 베스트셀러!"),
    BLT(14L, "비엘티", 7500L, "오리지널 아메리칸 스타일 베이컨의 풍미와 바삭함 그대로~"),
    ChickenSlice(15L, "치킨 슬라이스", 7500L, "닭가슴살로 만든 치킨 슬라이스로 즐기는 담백한 맛!"),
    Tuna(16L, "참치", 7500L, "남녀노소 누구나 좋아하는 담백한 참치와 고소한 마요네즈의 완벽한 조화"),
    Ham(17L, "햄", 7500L, "기본 중에 기본! 풍부한 햄 토핑 가득 샐러드"),
    EggMayo(18L, "에그마요", 7500L, "부드러운 달걀과 고소한 마요네즈가 만나 더 부드러운 스테디셀러"),
    VeggieDelite(19L, "베지", 7500L, "| 7가지 야채만으로도 깔끔한 조화! 기본에 충실한 베지샐러드");

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

    Salad(Long id, String name, Long price, String productDetails) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productDetails = productDetails;
    }
}
