package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.OrderRequest;
import com.nhnacademy.shoppingmall.domain.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getAllOrders(String userId);
    OrderResponse getOrder(Integer orderId);
    void createOrder(OrderRequest orderRequest);
    void deleteOrder(Integer orderId);
}
