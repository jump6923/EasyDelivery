package com.sparta.easydelivery.common.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;

public class UnauthorizedUserException extends DomainException {
    public UnauthorizedUserException(){
        super(ErrorCode.UNAUTHORIZED_USER, ErrorCode.UNAUTHORIZED_USER.getMessage());
    }
}
