package com.sparta.easydelivery.domain.cart.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;

public class DuplicatedProductInCartException extends DomainException {
    public DuplicatedProductInCartException(){
        super(ErrorCode.DUPLICATED_PRODUCT_IN_CART.getCode().value(), ErrorCode.DUPLICATED_PRODUCT_IN_CART.getMessage());
    }
}
