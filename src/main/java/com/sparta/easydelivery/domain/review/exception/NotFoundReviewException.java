package com.sparta.easydelivery.domain.review.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;

public class NotFoundReviewException extends DomainException {
    public NotFoundReviewException(){
        super(ErrorCode.NOT_FOUND_REVIEW.getCode().value(), ErrorCode.NOT_FOUND_REVIEW.getMessage());
    }
}
