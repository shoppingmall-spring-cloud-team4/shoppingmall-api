package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail, OrderDetail.Pk> {
}
