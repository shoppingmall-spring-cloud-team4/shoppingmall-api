package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.domain.ShoppingCartDto;
import com.nhnacademy.shoppingmall.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    List<ShoppingCartDto> getAllByRecordId(Integer recordId);
}
