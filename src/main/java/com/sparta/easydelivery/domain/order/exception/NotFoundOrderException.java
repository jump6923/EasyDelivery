package com.sparta.easydelivery.domain.order.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;

public class NotFoundOrderException extends DomainException {
    public NotFoundOrderException(){
        super(ErrorCode.NOT_FOUND_ORDER.getCode().value(), ErrorCode.NOT_FOUND_ORDER.getMessage());
    }
}
