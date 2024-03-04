package com.example.login.loginexam.exception;

import com.example.login.loginexam.domain.enums.JwtExceptionStatus;
import lombok.Getter;

@Getter
public class JwtAuthenticationException extends RuntimeException {

    private JwtExceptionStatus status;

    public JwtAuthenticationException() {
        super();
    }

    public JwtAuthenticationException(JwtExceptionStatus status) {
        super(status.getMessage());
        this.status = status;
    }

    /*
        Exception File Trace 비용 발생 방지
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
