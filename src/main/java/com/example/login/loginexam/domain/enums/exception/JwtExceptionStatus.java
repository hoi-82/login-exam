package com.example.login.loginexam.domain.enums.exception;

import com.example.login.loginexam.domain.dto.CommonResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JwtExceptionStatus {
    EXPIRATION_TOKEN(414, "Token is expired, you need reissue")
    , INVALID_TOKEN(403, "Invalid JWT token")
    , INVALID_BEARER_TOKEN(403, "Invalid bearer token")
    , CAN_NOT_FIND_USER(403, "Can't not find user")
    ;

    private final Integer code;
    private final String message;

    public CommonResponse<Void> toCommonResponse() {
        return new CommonResponse<>(this.code, this.message, null);
    }
}
