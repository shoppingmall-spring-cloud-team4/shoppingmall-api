package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.OrderRequest;
import com.nhnacademy.shoppingmall.domain.OrderResponse;
import com.nhnacademy.shoppingmall.exception.OrderNotFoundException;
import com.nhnacademy.shoppingmall.exception.ProductNotFoundException;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderResponse> getAllOrders(String userId) throws OrderNotFoundException;
    Optional<OrderResponse> getOrder(Integer orderId) throws OrderNotFoundException;
    void createOrder(OrderRequest orderRequest) throws ProductNotFoundException;
    void deleteOrder(Integer orderId) throws OrderNotFoundException;
}
