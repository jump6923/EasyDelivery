package com.sparta.easydelivery.user.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;

public class InvalidTokenException extends DomainException {
    public InvalidTokenException(){
        super(ErrorCode.INVALID_TOKEN, ErrorCode.INVALID_TOKEN.getMessage());
    }
}
