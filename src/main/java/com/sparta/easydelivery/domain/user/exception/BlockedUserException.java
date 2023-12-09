package com.sparta.easydelivery.domain.user.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;

public class BlockedUserException extends DomainException {
    public BlockedUserException(){
        super(ErrorCode.BLOCKED_USER.getCode().value(), ErrorCode.BLOCKED_USER.getMessage());
    }
}
