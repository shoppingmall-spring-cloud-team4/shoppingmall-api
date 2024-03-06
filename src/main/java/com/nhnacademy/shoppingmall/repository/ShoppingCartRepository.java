package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.domain.ShoppingCartDto;
import com.nhnacademy.shoppingmall.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    @Query("SELECT new com.nhnacademy.shoppingmall.domain.ShoppingCartDto(s.product.productId, s.quantity) FROM ShoppingCart s WHERE s.recordId = :recordId")
    List<ShoppingCartDto> getAllByRecordId(@Param("recordId") Integer recordId);
}
