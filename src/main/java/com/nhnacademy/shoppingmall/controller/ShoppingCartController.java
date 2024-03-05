package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.domain.ShoppingCartDto;
import com.nhnacademy.shoppingmall.domain.ShoppingCartRegisterDto;
import com.nhnacademy.shoppingmall.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/{userId}/shoppingCart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping("/{recordId}")
    public ResponseEntity<List<ShoppingCartDto>> getAllShoppingList(@PathVariable Integer recordId) {
        return ResponseEntity.ok().body(shoppingCartService.getAllShoppingList(recordId));
    }

    @PostMapping
    public ResponseEntity<Void> createShoppingCart(@RequestBody ShoppingCartRegisterDto shoppingCartRegisterDto) {
        shoppingCartService.createShoppingCart(shoppingCartRegisterDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{recordId}")
    public ResponseEntity<Void> updateShoppingCart(@RequestBody ShoppingCartRegisterDto shoppingCartRegisterDto, @PathVariable Integer recordId) {
        shoppingCartService.updateShppoingCart(shoppingCartRegisterDto, recordId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<Void> deleteShoppingCart(@PathVariable Integer recordId) {
        shoppingCartService.deleteShppoingCart(recordId);
        return ResponseEntity.ok().build();
    }
}
