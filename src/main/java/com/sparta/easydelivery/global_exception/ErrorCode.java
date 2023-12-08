package com.sparta.easydelivery.global_exception;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Locale;

@Getter
public enum ErrorCode {
    /* FILTER */
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "token.expired"),

    /* COMMON */
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "unauthorized.user"),
    INVALIDATE_INPUT(HttpStatus.BAD_REQUEST, "invalidate.input"),

    /* USER */
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "not.found.user"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "invalid.password"),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "invalid.token"),
    DUPLICATED_USERNAME(HttpStatus.CONFLICT, "duplicated.username"),
    BLOCKED_USER(HttpStatus.FORBIDDEN, "blocked.user"),
    DUPLICATED_PASSWORD(HttpStatus.CONFLICT, "duplicated.password"),

    /* PRODUCT */
    NOT_FOUND_PRODUCT(HttpStatus.NOT_FOUND, "not.found.product"),

    /* CART */
    NOT_FOUND_CART(HttpStatus.NOT_FOUND, "not.found.cart"),
    DUPLICATED_PRODUCT_IN_CART(HttpStatus.CONFLICT, "duplicated.product.in.cart"),

    /* ORDER */
    NOT_FOUND_ORDER(HttpStatus.NOT_FOUND, "not.found.order"),
    EMPTY_CART(HttpStatus.BAD_REQUEST, "empty.cart"),

    /* REVIEW */
    NOT_FOUND_REVIEW(HttpStatus.NOT_FOUND, "not.found.review"),
    NOT_COMPLETED_ORDER(HttpStatus.BAD_REQUEST, "not.completed.order"),
    DUPLICATED_REVIEW(HttpStatus.CONFLICT, "duplicated.review");


    private final HttpStatus code;

    private String message;

    ErrorCode(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * MessageSource를 적용하는 경우 필요
     */
    @RequiredArgsConstructor
    @Component
    public static class ErrorReasonInjector {

        private final MessageSource messageSource;

//		@Value("${application.locale}")
//		private String locale;

        @PostConstruct
        public void postConstruct() {
            Arrays.stream(ErrorCode.values())
                .forEach(errorcode -> errorcode.message = messageSource.getMessage(errorcode.message,null, Locale.getDefault()));
        }
    }
}
