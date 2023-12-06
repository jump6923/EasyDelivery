package com.sparta.easydelivery.order.exception;

public class OrderNothingException extends RuntimeException{
    public OrderNothingException(String msg){
        super(msg);
    }
}
