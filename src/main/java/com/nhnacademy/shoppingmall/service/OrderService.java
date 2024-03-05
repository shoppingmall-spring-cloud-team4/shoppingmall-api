package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.CategoryRequest;
import com.nhnacademy.shoppingmall.domain.CategoryResponse;
import com.nhnacademy.shoppingmall.domain.OrderRequest;
import com.nhnacademy.shoppingmall.domain.OrderResponse;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderResponse> getAllOrders();
    Optional<OrderResponse> getOrder(Integer orderId);
    OrderResponse createOrder(OrderRequest orderRequest);
    void deleteOrder(Integer orderId);
}
