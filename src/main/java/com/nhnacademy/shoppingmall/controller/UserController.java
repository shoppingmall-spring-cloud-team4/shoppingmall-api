package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.domain.UserDto;
import com.nhnacademy.shoppingmall.domain.UserRegisterDto;
import com.nhnacademy.shoppingmall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllPagingUsers(Pageable pageable)
    {
        return ResponseEntity.ok().body(userService.getPagingUsers(pageable).getContent());
    }

    @GetMapping("/myPage")
    public ResponseEntity<Optional<UserDto>> getUser(@RequestHeader("X-USER-ID") String userId)
    {
        return ResponseEntity.ok().body(userService.getUserById(userId));
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserRegisterDto userRegisterDto) {
        userService.createUser(userRegisterDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@RequestBody UserRegisterDto userRegisterDto, @RequestHeader("X-USER-ID") String userId) {
        userService.updateUser(userRegisterDto, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestHeader("X-USER-ID") String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
