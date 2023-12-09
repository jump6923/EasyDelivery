package com.sparta.easydelivery.domain.review.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;

public class NotCompletedOrderException extends DomainException {
    public NotCompletedOrderException(){
        super(ErrorCode.NOT_COMPLETED_ORDER.getCode().value(), ErrorCode.NOT_COMPLETED_ORDER.getMessage());
    }
}
