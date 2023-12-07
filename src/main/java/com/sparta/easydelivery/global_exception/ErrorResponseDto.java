package com.sparta.easydelivery.global_exception;

import lombok.Data;

@Data
public class ErrorResponseDto {
    private int statusCode;
    private String message;

    public ErrorResponseDto(ErrorCode error, String message) {
        this.statusCode = error.getCode().value();
        this.message = message;
    }

    public ErrorResponseDto(DomainException domainException){
        this.statusCode = domainException.getErrorCode();
        this.message = domainException.getMessage();
    }
}