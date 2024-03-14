package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.domain.ShoppingCartDto;
import com.nhnacademy.shoppingmall.domain.ShoppingCartRegisterDto;
import com.nhnacademy.shoppingmall.domain.ShoppingCartUpdateDto;
import com.nhnacademy.shoppingmall.entity.Product;
import com.nhnacademy.shoppingmall.entity.ShoppingCart;
import com.nhnacademy.shoppingmall.entity.User;
import com.nhnacademy.shoppingmall.exception.ProductNotFoundException;
import com.nhnacademy.shoppingmall.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.repository.ProductRepository;
import com.nhnacademy.shoppingmall.repository.ShoppingCartRepository;
import com.nhnacademy.shoppingmall.repository.UserRepository;
import com.nhnacademy.shoppingmall.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public List<ShoppingCartDto> getAllShoppingList(String userId) {
        return shoppingCartRepository.getAllByUser_UserId(userId);
    }

    @Override
    public void createShoppingCart(ShoppingCartRegisterDto shoppingCartRegisterDto) {
        String userId = shoppingCartRegisterDto.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        Integer productId = shoppingCartRegisterDto.getProductId();
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));

        ShoppingCart shoppingCart = ShoppingCart.builder()
                        .dateCreated(LocalDateTime.now())
                        .product(product)
                        .user(user)
                        .quantity(shoppingCartRegisterDto.getQuantity()).build();

        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void updateShppoingCart(ShoppingCartUpdateDto shoppingCartUpdateDto, Integer recordId) {
        ShoppingCart existedShoppingcart = shoppingCartRepository.findById(recordId).orElse(null);

        if(existedShoppingcart != null)
        {
            ShoppingCart shoppingCart = ShoppingCart.builder()
                    .recordId(recordId)
                    .dateCreated(LocalDateTime.now())
                    .product(existedShoppingcart.getProduct())
                    .user(existedShoppingcart.getUser())
                    .quantity(shoppingCartUpdateDto.getQuantity()).build();

            shoppingCartRepository.save(shoppingCart);
        }
    }

    @Override
    public void deleteShppoingCart(Integer recordId) {
        shoppingCartRepository.deleteById(recordId);
    }
}
