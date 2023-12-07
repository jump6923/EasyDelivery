package com.sparta.easydelivery.user.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;

public class DuplicatedUsernameException extends DomainException {
    public DuplicatedUsernameException(){
        super(ErrorCode.DUPLICATED_USERNAME.getCode().value(), ErrorCode.DUPLICATED_USERNAME.getMessage());
    }
}
