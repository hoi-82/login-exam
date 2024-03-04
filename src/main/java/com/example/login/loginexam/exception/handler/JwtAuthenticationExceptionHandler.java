package com.example.login.loginexam.exception.handler;

import com.example.login.loginexam.domain.dto.CommonResponse;
import com.example.login.loginexam.domain.enums.JwtExceptionStatus;
import com.example.login.loginexam.exception.JwtAuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JwtAuthenticationExceptionHandler {

    /**
     * JwtAuthenticationException 예외처리 핸들러
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<CommonResponse> jwtAuthenticationExceptionAdvice(JwtAuthenticationException exception) {
        JwtExceptionStatus status = exception.getStatus();
        return ResponseEntity.status(status.getCode()).body(status.toCommonResponse());
    }
}
