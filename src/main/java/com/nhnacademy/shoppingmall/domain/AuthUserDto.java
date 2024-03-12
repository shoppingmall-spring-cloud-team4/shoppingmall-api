package com.nhnacademy.shoppingmall.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class AuthUserDto {
    private String userId;
    private String userName;
    private String userPassword;
    private String userAuth;

}
