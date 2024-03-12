package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.domain.AuthUserDto;
import com.nhnacademy.shoppingmall.domain.UserDto;
import com.nhnacademy.shoppingmall.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    List<UserDto> getAllBy();


    Optional<AuthUserDto> getByUserId(String userId);
}
