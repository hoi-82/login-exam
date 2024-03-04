package com.example.login.loginexam.config;

import com.example.login.loginexam.domain.vo.RedisConfigurationKey;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories
@Configuration
@RequiredArgsConstructor
public class RedisConfig {
    private final RedisConfigurationKey key;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(key.getHost(), key.getPort());
    }
}
