package com.sparta.easydelivery.domain.user.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;

public class InvalidTokenException extends DomainException {
    public InvalidTokenException(){
        super(ErrorCode.INVALID_TOKEN.getCode().value(), ErrorCode.INVALID_TOKEN.getMessage());
    }
}
