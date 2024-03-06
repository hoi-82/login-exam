package com.example.login.loginexam.domain.enums.exception;

import com.example.login.loginexam.domain.dto.CommonResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ServerExceptionStatus {
    SIGNUP_ERROR(500, "계정 등록에 실패하였습니다. 다시 시도하거나 관리자에게 문의해주세요.")
    , SIGNUP_EXIST_EMAIL_ERROR(500, "이미 등록된 계정입니다.")
    , LOGIN_ERROR(400, "등록된 계정이 아니거나 비밀번호가 맞지 않습니다.")
    , INTERNAL_ERROR(500, "서버에 문제가 발생했습니다. 다시 시도하거나 관리자에게 문의해주세요.")
    ;

    private final Integer code;
    private final String message;

    public CommonResponse<Void> toCommonResponse() {
        return new CommonResponse<>(this.code, this.message, null);
    }
}
