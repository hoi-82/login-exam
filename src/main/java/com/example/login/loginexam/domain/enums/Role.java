package com.example.login.loginexam.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("ROLE_ADMIN", "관리자")
    , STAFF("ROLE_STAFF", "스태프")
    , USER("ROLE_USER", "사용자")
    , GUEST("ROLE_GUEST", "게스트")
    ;

    private final String key;
    private final String name;
}
