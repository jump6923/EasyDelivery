package com.sparta.easydelivery.cart.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;

public class NotFoundCartException extends DomainException {
    public NotFoundCartException(){
        super(ErrorCode.NOT_FOUND_CART.getCode().value(), ErrorCode.NOT_FOUND_CART.getMessage());
    }
}
