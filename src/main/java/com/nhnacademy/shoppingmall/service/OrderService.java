package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.OrderRequest;
import com.nhnacademy.shoppingmall.domain.OrderResponse;
import com.nhnacademy.shoppingmall.exception.OrderNotFoundException;
import com.nhnacademy.shoppingmall.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
    List<OrderResponse> getAllOrders(String userId);
    OrderResponse getOrder(Integer orderId);
    void createOrder(OrderRequest orderRequest);
    void deleteOrder(Integer orderId);
}
