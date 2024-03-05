package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.domain.OrderRequest;
import com.nhnacademy.shoppingmall.domain.OrderResponse;
import com.nhnacademy.shoppingmall.service.OrderService;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    @Override
    public List<OrderResponse> getAllOrders() {
        return null;
    }

    @Override
    public Optional<OrderResponse> getOrder(Integer orderId) {
        return Optional.empty();
    }

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        return null;
    }

    @Override
    public void deleteOrder(Integer orderId) {

    }
}
