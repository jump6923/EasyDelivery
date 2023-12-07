package com.sparta.easydelivery.order.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;

public class EmptyCartException extends DomainException {
    public EmptyCartException(){
        super(ErrorCode.EMPTY_CART.getCode().value(), ErrorCode.EMPTY_CART.getMessage());
    }
}
