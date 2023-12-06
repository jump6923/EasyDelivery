package com.sparta.easydelivery.order.exception;

public class NotMatchUserException extends RuntimeException{
    public NotMatchUserException(String msg){
        super(msg);
    }
}
