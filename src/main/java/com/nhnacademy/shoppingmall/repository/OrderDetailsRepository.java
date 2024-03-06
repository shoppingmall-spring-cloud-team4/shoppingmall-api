package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.domain.OrderedProductDto;
import com.nhnacademy.shoppingmall.entity.Order;
import com.nhnacademy.shoppingmall.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail, OrderDetail.Pk> {
    List<OrderDetail> findAllByOrder_OrderId(Integer orderId);
}
