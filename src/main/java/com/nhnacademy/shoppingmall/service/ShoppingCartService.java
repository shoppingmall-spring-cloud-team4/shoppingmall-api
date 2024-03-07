package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.ShoppingCartDto;
import com.nhnacademy.shoppingmall.domain.ShoppingCartRegisterDto;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCartDto> getAllShoppingList(Integer recordId);

    void createShoppingCart(ShoppingCartRegisterDto shoppingCartRegisterDto);

    void updateShppoingCart(ShoppingCartRegisterDto shoppingCartRegisterDto, Integer recordId);

    void deleteShppoingCart(Integer recordId);

}