package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.AuthUserDto;
import com.nhnacademy.shoppingmall.domain.UserDto;
import com.nhnacademy.shoppingmall.domain.UserRegisterDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getUsers();
    Optional<AuthUserDto> getUserById(String userId);
    void createUser(UserRegisterDto userRegisterDto);
    void updateUser(UserRegisterDto userRegisterDto, String userId);
    void deleteUser(String userId);
}
