package com.example.login.loginexam.domain.entity;

import com.example.login.loginexam.domain.dto.RegisterUserDto;
import com.example.login.loginexam.domain.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotNull(message = "사용자 이메일 주소가 입력되지 않았습니다.")
    @Column(name = "email", unique = true)
    private String emailAddress;

    @NotNull(message = "사용자 명이 입력되지 않았습니다.")
    @Column(name = "name")
    private String userName;

    @NotNull(message = "사용자 비밀번호가 입력되지 않았습니다.")
    @Column(name = "password")
    private String password;

    @NotNull(message = "사용자 권한이 설정되지 않았습니다.")
    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean tos; // 이용 약관 동의

    private boolean pic; // 개인 정보 동의

    private boolean expired; // 계정 만료 (계정 삭제 여부)

    private boolean locked; // 계정 잠김

    private boolean credentialExpired; // 비밀번호 만료

    private boolean enabled; // 계정 활성화

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public User(String emailAddress, String userName, String password, Role role, boolean tos, boolean pic, boolean expired, boolean locked, boolean credentialExpired, boolean enabled) {
        this.emailAddress = emailAddress;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.tos = tos;
        this.pic = pic;
        this.expired = expired;
        this.locked = locked;
        this.credentialExpired = credentialExpired;
        this.enabled = enabled;
    }

    // 사용자 등록 DTO to Entity
    public static User dtoToEntity(RegisterUserDto rgDto) throws Exception {
        return User.builder()
                .emailAddress(rgDto.emailAddress())
                .userName(rgDto.userName())
                .password(rgDto.password())
                .role(rgDto.role())
                .tos(rgDto.tos())
                .pic(rgDto.pic())
                .expired(false)
                .locked(false)
                .credentialExpired(false)
                .enabled(false)
                .build();
    }
}
