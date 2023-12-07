package com.sparta.easydelivery.global_exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    /* COMMON */
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "권한이 없습니다."),
    INVALIDATE_INPUT(HttpStatus.BAD_REQUEST, "입력 형식에 맞지 않습니다."),

    /* USER */
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "토큰이 일치하지 않습니다."),
    DUPLICATED_USERNAME(HttpStatus.CONFLICT, "이미 존재하는 username입니다."),

    /* PRODUCT */
    NOT_FOUND_PRODUCT(HttpStatus.NOT_FOUND, "해당 상품을 찾을 수 없습니다."),

    /* CART */
    NOT_FOUND_CART(HttpStatus.NOT_FOUND, "해당 장바구니를 찾을 수 없습니다."),
    DUPLICATED_PRODUCT_IN_CART(HttpStatus.CONFLICT, "해당 상품은 이미 장바구니에 담겨 있습니다."),

    /* ORDER */
    NOT_FOUND_ORDER(HttpStatus.NOT_FOUND, "해당 주문을 찾을 수 없습니다."),
    EMPTY_CART(HttpStatus.BAD_REQUEST, "장바구니에 주문할 상품이 없습니다."),

    /* REVIEW */
    NOT_FOUND_REVIEW(HttpStatus.NOT_FOUND, "해당 리뷰를 찾을 수 없습니다."),
    NOT_COMPLETED_ORDER(HttpStatus.BAD_REQUEST, "아직 주문이 완료되지 않았습니다."),
    DUPLICATED_REVIEW(HttpStatus.CONFLICT, "해당 주문에 대한 리뷰가 존재합니다.");

    private final HttpStatus code;

    private String message;

    ErrorCode(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * MessageSource를 적용하는 경우 필요
     */
//    @RequiredArgsConstructor
//    @Component
//    public static class ErrorReasonInjector {
//
//        private final MessageSource messageSource;
//
////		@Value("${application.locale}")
////		private String locale;
//
//        @PostConstruct
//        public void postConstruct() {
//            Arrays.stream(ErrorCode.values())
//                .forEach(errorcode -> errorcode.message = messageSource.getMessage(errorcode.message,null, Locale.getDefault()));
//        }
//    }
}
