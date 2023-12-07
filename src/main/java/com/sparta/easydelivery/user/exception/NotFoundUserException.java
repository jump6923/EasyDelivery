package com.sparta.easydelivery.user.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;

public class NotFoundUserException extends DomainException {
    public NotFoundUserException(){
        super(ErrorCode.NOT_FOUND_USER.getCode().value(), ErrorCode.NOT_FOUND_USER.getMessage());
    }
}
