package com.example.login.loginexam.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfigurationKey {
    private String host;
    private int port;
}
