package com.sparta.easydelivery.order.exception;

public class UnauthorizedAccessException extends RuntimeException{
    public UnauthorizedAccessException(String msg){
        super(msg);
    }
}
