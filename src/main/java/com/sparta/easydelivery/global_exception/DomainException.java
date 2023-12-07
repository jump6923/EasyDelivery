package com.sparta.easydelivery.global_exception;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String message;

    public DomainException(ErrorCode errorCode, String message){
        this.errorCode = errorCode;
        this.message = message;
    }
}
