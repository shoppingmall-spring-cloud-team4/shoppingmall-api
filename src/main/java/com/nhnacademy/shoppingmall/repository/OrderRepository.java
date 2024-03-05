package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
