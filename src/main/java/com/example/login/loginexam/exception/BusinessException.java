package com.example.login.loginexam.exception;

import com.example.login.loginexam.domain.enums.exception.JwtExceptionStatus;
import com.example.login.loginexam.domain.enums.exception.ServerExceptionStatus;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private ServerExceptionStatus status;

    public BusinessException() {
        super();
        status = ServerExceptionStatus.INTERNAL_ERROR;
    }

    public BusinessException(ServerExceptionStatus status) {
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
