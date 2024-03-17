package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.UserDto;
import com.nhnacademy.shoppingmall.domain.UserRegisterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getUsers();
    Page<UserDto> getPagingUsers(Pageable pageable);
    Optional<UserDto> getUserById(String userId);
    void createUser(UserRegisterDto userRegisterDto);
    void updateUser(UserRegisterDto userRegisterDto, String userId);
    void deleteUser(String userId);
}
