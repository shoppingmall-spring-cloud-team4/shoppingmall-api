package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
}
