package com.sparta.easydelivery.review.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;

public class NotCompletedOrderException extends DomainException {
    public NotCompletedOrderException(){
        super(ErrorCode.NOT_COMPLETED_ORDER, ErrorCode.NOT_COMPLETED_ORDER.getMessage());
    }
}
