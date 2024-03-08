package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.ShoppingCartDto;
import com.nhnacademy.shoppingmall.domain.ShoppingCartRegisterDto;
import com.nhnacademy.shoppingmall.domain.ShoppingCartUpdateDto;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCartDto> getAllShoppingList(String userId);

    void createShoppingCart(ShoppingCartRegisterDto shoppingCartRegisterDto);

    void updateShppoingCart(ShoppingCartUpdateDto shoppingCartUpdateDto, Integer recordId);

    void deleteShppoingCart(Integer recordId);

}