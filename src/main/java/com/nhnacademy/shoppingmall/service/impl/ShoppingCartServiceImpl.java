package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.domain.ShoppingCartDto;
import com.nhnacademy.shoppingmall.domain.ShoppingCartRegisterDto;
import com.nhnacademy.shoppingmall.entity.ShoppingCart;
import com.nhnacademy.shoppingmall.repository.ShoppingCartRepository;
import com.nhnacademy.shoppingmall.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public List<ShoppingCartDto> getAllShoppingList(Integer recordId) {
        return shoppingCartRepository.getAllByRecordId(recordId);
    }

    @Override
    public void createShoppingCart(ShoppingCartRegisterDto shoppingCartRegisterDto) {
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartRegisterDto, shoppingCart);
        shoppingCart.setDateCreated(LocalDateTime.now());

        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void updateShppoingCart(ShoppingCartRegisterDto shoppingCartRegisterDto, Integer recordId) {
        ShoppingCart existedShoppingcart = shoppingCartRepository.findById(recordId).orElse(null);

        if(existedShoppingcart != null)
        {
            BeanUtils.copyProperties(shoppingCartRegisterDto, existedShoppingcart);
            shoppingCartRepository.save(existedShoppingcart);
        }
    }

    @Override
    public void deleteShppoingCart(Integer recordId) {
        shoppingCartRepository.deleteById(recordId);
    }
}
