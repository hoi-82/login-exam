package com.example.login.loginexam.exception.handler;

import com.example.login.loginexam.domain.dto.CommonResponse;
import com.example.login.loginexam.domain.enums.exception.ServerExceptionStatus;
import com.example.login.loginexam.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonRestExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CommonResponse<Void>> exceptionHandler(BusinessException e) {
        ServerExceptionStatus status = e.getStatus();
        return ResponseEntity.status(status.getCode()).body(status.toCommonResponse());
    }
}
