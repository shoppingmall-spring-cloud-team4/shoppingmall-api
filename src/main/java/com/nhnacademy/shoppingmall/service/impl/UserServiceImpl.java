package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.domain.UserDto;
import com.nhnacademy.shoppingmall.domain.UserRegisterDto;
import com.nhnacademy.shoppingmall.entity.User;
import com.nhnacademy.shoppingmall.repository.UserRepository;
import com.nhnacademy.shoppingmall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDto> getUsers() {
        return userRepository.getAllBy();
    }

    @Override
    public Optional<UserDto> getUserById(String userId) {
        return userRepository.getByUserId(userId);
    }

    @Override
    public void createUser(UserRegisterDto userRegisterDto) {
        User user = User.builder()
                .createdAt(LocalDateTime.now())
                .userPoint(1000000)
                .userAuth("user")
                .build();

        BeanUtils.copyProperties(userRegisterDto, user);
        userRepository.save(user);
    }

    @Override
    public void updateUser(UserRegisterDto userRegisterDto, String userId) {
        User existedUser = userRepository.findById(userId).orElse(null);

        if (existedUser != null) {
            BeanUtils.copyProperties(userRegisterDto, existedUser);
            userRepository.save(existedUser);
        }
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}