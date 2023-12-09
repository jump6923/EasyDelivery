package com.sparta.easydelivery.domain.product.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;

public class NotEnoughStockException extends DomainException {

    public NotEnoughStockException() {
        super(ErrorCode.NOT_ENOUGH_STOCK.getCode().value(), ErrorCode.NOT_ENOUGH_STOCK.getMessage());
    }
}
