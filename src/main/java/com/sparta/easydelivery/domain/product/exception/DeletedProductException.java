package com.sparta.easydelivery.domain.product.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;

public class DeletedProductException extends DomainException {

    public DeletedProductException() {
        super(ErrorCode.DELETED_PRODUCT.getCode().value(), ErrorCode.DELETED_PRODUCT.getMessage());
    }
}
