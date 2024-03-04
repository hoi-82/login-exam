package com.example.login.loginexam.domain.enums;

import com.example.login.loginexam.domain.dto.CommonResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JwtExceptionStatus {
    INVALID_TOKEN(403, "Invalid JWT token")
    , INVALID_BEARER_TOKEN(403, "Invalid bearer token")
    , CAN_NOT_FIND_USER(403, "Can't not find user")
    ;

    private final Integer code;
    private final String message;

    public CommonResponse toCommonResponse() {
        return new CommonResponse(this.code, this.message);
    }
}
