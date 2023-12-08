package com.sparta.easydelivery.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.easydelivery.global_exception.ErrorCode;
import com.sparta.easydelivery.global_exception.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
        AccessDeniedException accessDeniedException) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        ErrorResponseDto responseDto = new ErrorResponseDto(ErrorCode.ACCESS_DENIED,
            ErrorCode.ACCESS_DENIED.getMessage());
        response.getWriter().write(objectMapper.writeValueAsString(responseDto));
    }
}
