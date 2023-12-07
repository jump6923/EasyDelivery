package com.sparta.easydelivery.review.exception;

import com.sparta.easydelivery.global_exception.DomainException;
import com.sparta.easydelivery.global_exception.ErrorCode;

public class DuplicatedReviewException extends DomainException {
    public DuplicatedReviewException(){
        super(ErrorCode.DUPLICATED_REVIEW, ErrorCode.DUPLICATED_REVIEW.getMessage());
    }
}
