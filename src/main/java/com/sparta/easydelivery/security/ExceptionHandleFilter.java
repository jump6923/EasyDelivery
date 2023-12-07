package com.sparta.easydelivery.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.easydelivery.global_exception.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

public class ExceptionHandleFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request, response);
        }catch (ExpiredJwtException e){
            //토큰의 유효기간 만료
            setErrorResponse(response, ErrorCode.TOKEN_EXPIRED);
        }catch (JwtException | IllegalArgumentException | SecurityException e){
            //유효하지 않은 토큰
            setErrorResponse(response, ErrorCode.INVALID_TOKEN);
        }

    }
    private void setErrorResponse(HttpServletResponse response, ErrorCode errorCode){
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(errorCode.getCode().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getCode().value(), errorCode.getMessage());
        try{
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public record ErrorResponse(Integer code, String message) {

    }

}