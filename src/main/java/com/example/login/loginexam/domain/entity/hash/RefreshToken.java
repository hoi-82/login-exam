package com.example.login.loginexam.domain.entity.hash;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash(value = "refresh_token")
@Getter
public class RefreshToken {
    @Id
    private final String id;

    private final String token;

    @TimeToLive
    private final Long expirationInSeconds;

    @Builder
    public RefreshToken(String id, String token, Long expirationInSeconds) {
        this.id = id;
        this.token = token;
        this.expirationInSeconds = expirationInSeconds;
    }
}
