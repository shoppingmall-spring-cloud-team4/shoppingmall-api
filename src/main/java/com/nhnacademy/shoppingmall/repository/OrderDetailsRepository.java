package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetail, OrderDetail.Pk> {
    List<OrderDetail> findAllByOrder_OrderId(Integer orderId);
    void deleteAllByOrder_OrderId(Integer orderId);
}
