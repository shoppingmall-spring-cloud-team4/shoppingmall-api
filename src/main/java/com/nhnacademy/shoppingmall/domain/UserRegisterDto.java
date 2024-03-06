package com.nhnacademy.shoppingmall.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegisterDto {
    private String userId;
    private String userName;
    private String userPassword;
    private String userBirth;
}
