package com.nhnacademy.shoppingmall.controller;


import com.nhnacademy.shoppingmall.domain.AuthUserDto;
import com.nhnacademy.shoppingmall.domain.ReviewDto;
import com.nhnacademy.shoppingmall.domain.UserDto;
import com.nhnacademy.shoppingmall.domain.UserRegisterDto;
import com.nhnacademy.shoppingmall.service.ReviewService;
import com.nhnacademy.shoppingmall.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/shop/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Optional<AuthUserDto>> getUser(@PathVariable("userId") String userId) {
        AuthUserDto authUserDto = userService.getUserById(userId).orElse(null);
        log.info("{}",authUserDto);
        return ResponseEntity.ok().body(userService.getUserById(userId));
    }

    // User가 작성한 reviews
    @GetMapping("/{userId}/review")
    public ResponseEntity<List<ReviewDto>> getReviewsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok().body(reviewService.getReviewsByUserId(userId));
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
