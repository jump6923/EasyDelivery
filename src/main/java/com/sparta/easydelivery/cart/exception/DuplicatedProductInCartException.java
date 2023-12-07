package com.sparta.easydelivery.cart.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;

public class DuplicatedProductInCartException extends DomainException {
    public DuplicatedProductInCartException(){
        super(ErrorCode.DUPLICATED_PRODUCT_IN_CART, ErrorCode.DUPLICATED_PRODUCT_IN_CART.getMessage());
    }
}
