package com.example.login.loginexam.repository;

import com.example.login.loginexam.domain.entity.hash.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface JwtCachingRepository extends CrudRepository<RefreshToken, String> {
}
