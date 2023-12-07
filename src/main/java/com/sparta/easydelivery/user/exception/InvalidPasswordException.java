package com.sparta.easydelivery.user.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;
import org.springframework.jmx.access.InvalidInvocationException;

public class InvalidPasswordException extends DomainException {
    public InvalidPasswordException(){
        super(ErrorCode.INVALID_PASSWORD.getCode().value(), ErrorCode.INVALID_PASSWORD.getMessage());
    }
}
