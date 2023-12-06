package com.sparta.easydelivery.order.entity;

public enum OrderStatusEnum {
    PREPARATION("STATUS_PREPARATION"),
    DELIVERY("STATUS_DELIVERY"),
    COMPLETION("STATUS_COMPLETION");

    private final String orderStatus;

    OrderStatusEnum(String orderStatus) {
        this.orderStatus = orderStatus;
    }

}
