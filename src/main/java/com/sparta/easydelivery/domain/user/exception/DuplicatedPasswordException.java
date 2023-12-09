package com.sparta.easydelivery.domain.user.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;

public class DuplicatedPasswordException extends DomainException {
    public DuplicatedPasswordException(){
        super(ErrorCode.DUPLICATED_PASSWORD.getCode().value(), ErrorCode.DUPLICATED_PASSWORD.getMessage());
    }
}
