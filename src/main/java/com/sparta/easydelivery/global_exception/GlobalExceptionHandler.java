package com.sparta.easydelivery.global_exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // validation 오류 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationError(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder builder = new StringBuilder();
        for(FieldError fieldError : bindingResult.getFieldErrors()){
            builder.append("[");
            builder.append(fieldError.getField());
            builder.append("](은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력된 값: [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]");
        }
        ErrorResponseDto responseDto = new ErrorResponseDto(ErrorCode.INVALIDATE_INPUT, builder.toString());
        return ResponseEntity.status(responseDto.getStatusCode()).body(responseDto);
    }

    /**
     * 쿼리스트링으로 받는 값 검증에 대한 예외 (@Validated)
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleValidatedError(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        StringBuilder builder = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            String field = constraintViolation.getPropertyPath().toString();
            field = field.substring(field.lastIndexOf('.')+1);
            builder.append("[");
            builder.append(field);
            builder.append("](은)는 ");
            builder.append(constraintViolation.getMessage());
            builder.append(" 입력된 값: [");
            builder.append(constraintViolation.getInvalidValue());
            builder.append("]");
        }
        ErrorResponseDto responseDto = new ErrorResponseDto(ErrorCode.INVALIDATE_INPUT,
            builder.toString());
        return ResponseEntity.status(responseDto.getStatusCode()).body(responseDto);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponseDto> handleDomainError(DomainException ex){
        ErrorResponseDto responseDto = new ErrorResponseDto(ex);
        return ResponseEntity.status(responseDto.getStatusCode()).body(responseDto);
    }
}
