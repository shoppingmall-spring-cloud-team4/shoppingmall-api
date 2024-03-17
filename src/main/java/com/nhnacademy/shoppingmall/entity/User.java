package com.nhnacademy.shoppingmall.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Users")
public class User {

    @Length(max = 50)
    @Column(name = "user_id")
    @Id
    private String userId;

    @Column(name = "user_name")
    @Length(max = 50)
    private String userName; // 이름

    @Column(name = "user_password")
    private String userPassword; // 비밀번호

    @Column(name = "user_birth")
    @Length(min = 8, max = 8)
    private String userBirth; // 생일(8자리)

    @Column(name = "user_auth")
    @Length(max = 10)
    private String userAuth; // 권한

    @Column(name = "created_at")
    private LocalDateTime createdAt; // 회원가입일자

    @Column(name = "latest_login_at")
    private LocalDateTime latestLoginAt; // 마지막 접속일

    @Builder
    public User(String userId, String userName, String userPassword, String userBirth, String userAuth, LocalDateTime createdAt, LocalDateTime latestLoginAt) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userBirth = userBirth;
        this.userAuth = userAuth;
        this.createdAt = createdAt;
        this.latestLoginAt = latestLoginAt;
    }

}
