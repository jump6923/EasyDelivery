package com.sparta.easydelivery.order.exception;

public class NotFoundOrderException extends RuntimeException{
    public NotFoundOrderException(String msg){
        super(msg);
    }
}
