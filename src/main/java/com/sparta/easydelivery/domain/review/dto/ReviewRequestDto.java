package com.sparta.easydelivery.domain.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ReviewRequestDto {

    @NotBlank(message = "리뷰의 내용은 공백이 불가능합니다.")
    @Size(max = 300, message = "리뷰는 최대 300자 입니다.")
    private String content;

    @DecimalMin(value = "0.0", message = "별점은 0.0 이상 5.0 이하의 실수입니다.")
    @DecimalMax(value = "5.0", message = "별점은 0.0 이상 5.0 이하의 실수입니다.")
    private Float star;

    @NotNull(message = "주문 번호는 필수입니다.")
    private Long orderId;

}
