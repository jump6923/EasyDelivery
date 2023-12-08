package com.sparta.easydelivery.order.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;

public class AlreadyCompletedOrderException extends DomainException {
    public AlreadyCompletedOrderException(){
        super(ErrorCode.ALREADY_COMPLETED_ORDER.getCode().value(), ErrorCode.ALREADY_COMPLETED_ORDER.getMessage());
    }
}
