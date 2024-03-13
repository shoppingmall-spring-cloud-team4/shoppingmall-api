package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.domain.UserDto;
import com.nhnacademy.shoppingmall.domain.UserRegisterDto;
import com.nhnacademy.shoppingmall.entity.User;
import com.nhnacademy.shoppingmall.exception.UserAlreadyExistException;
import com.nhnacademy.shoppingmall.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.repository.UserRepository;
import com.nhnacademy.shoppingmall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<UserDto> getPagingUsers(Pageable pageable) {
        return userRepository.getPagesBy(pageable);
    }

    @Override
    public Optional<UserDto> getUserById(String userId) {
        return userRepository.getByUserId(userId);
    }

    @Override
    public void createUser(UserRegisterDto userRegisterDto) {
        String userId = userRegisterDto.getUserId();

        if(userRepository.existsById(userId))
            throw new UserAlreadyExistException(userId);

        User user = User.builder()
                .userId(userId)
                .userName(userRegisterDto.getUserName())
                .userPassword(userRegisterDto.getUserPassword())
                .userBirth(userRegisterDto.getUserBirth())
                .createdAt(LocalDateTime.now())
                .userPoint(1000000)
                .userAuth("ROLE_USER")
                .build();

        userRepository.save(user);
    }

    @Override
    public void updateUser(UserRegisterDto userRegisterDto, String userId) {
        User existedUser = userRepository.findById(userId).orElse(null);

        if (existedUser != null) {
            User user = User.builder()
                    .userId(userRegisterDto.getUserId())
                    .userName(userRegisterDto.getUserName())
                    .userPassword(userRegisterDto.getUserPassword())
                    .userBirth(userRegisterDto.getUserBirth())
                    .createdAt(existedUser.getCreatedAt())
                    .userPoint(existedUser.getUserPoint())
                    .userAuth(existedUser.getUserAuth())
                    .build();

            userRepository.save(user);
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
