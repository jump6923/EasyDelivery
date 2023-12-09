package com.sparta.easydelivery.domain.product.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;

public class NotFoundProductException extends DomainException {
    public NotFoundProductException(){
        super(ErrorCode.NOT_FOUND_PRODUCT.getCode().value(), ErrorCode.NOT_FOUND_PRODUCT.getMessage());
    }

}
