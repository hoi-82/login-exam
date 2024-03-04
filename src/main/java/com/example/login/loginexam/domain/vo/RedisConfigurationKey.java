package com.example.login.loginexam.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfigurationKey {
    private String host;
    private int port;
}
