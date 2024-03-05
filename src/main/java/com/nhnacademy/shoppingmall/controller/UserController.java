package com.nhnacademy.shoppingmall.controller;


import com.nhnacademy.shoppingmall.domain.UserDto;
import com.nhnacademy.shoppingmall.domain.UserRegisterDto;
import com.nhnacademy.shoppingmall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<UserDto>> getUser(@PathVariable("userId") String userId)
    {
        return ResponseEntity.ok().body(userService.getUserById(userId));
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserRegisterDto userRegisterDto) {
        userService.createUser(userRegisterDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@RequestBody UserRegisterDto userRegisterDto, @PathVariable("userId") String userId) {
        userService.updateUser(userRegisterDto, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
