package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.domain.OrderedProductDto;
import com.nhnacademy.shoppingmall.domain.OrderRequest;
import com.nhnacademy.shoppingmall.domain.OrderResponse;
import com.nhnacademy.shoppingmall.entity.*;
import com.nhnacademy.shoppingmall.exception.OrderNotFoundException;
import com.nhnacademy.shoppingmall.exception.ProductNotFoundException;
import com.nhnacademy.shoppingmall.repository.*;
import com.nhnacademy.shoppingmall.service.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository, UserRepository userRepository, AddressRepository addressRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<OrderResponse> getAllOrders(String userId) {
        List<Order> orders = orderRepository.findAllByUser_UserId(userId);
        List<OrderResponse> orderResponses = new ArrayList<>();

        for(Order order : orders){
            List<OrderDetail> orderDetails = orderDetailsRepository.findAllByOrder_OrderId(order.getOrderId());
            List<OrderedProductDto> orderedProducts = new ArrayList<>();

            for (OrderDetail orderDetail : orderDetails) {
                OrderedProductDto orderProductDto = new OrderedProductDto(orderDetail.getProduct().getProductId(), orderDetail.getQuantity(), orderDetail.getUnitCost());
                orderedProducts.add(orderProductDto);
            }

            OrderResponse orderResponse = new OrderResponse(order.getOrderId(), order.getOrderDate(), order.getShipDate(), userId, order.getAddress().getZipcode(), order.getAddress().getAddressDetail(), orderedProducts);
            orderResponses.add(orderResponse);
        }

        return orderResponses;
    }

    @Override
    public Optional<OrderResponse> getOrder(Integer orderId) throws OrderNotFoundException {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        List<OrderDetail> orderDetails = orderDetailsRepository.findAllByOrder_OrderId(orderId);
        List<OrderedProductDto> orderedProducts= new ArrayList<>();

        for (OrderDetail orderDetail : orderDetails) {
            OrderedProductDto orderedProductDto = new OrderedProductDto(orderDetail.getProduct().getProductId(), orderDetail.getQuantity(), orderDetail.getUnitCost());
            orderedProducts.add(orderedProductDto);
        }

        OrderResponse orderResponse = new OrderResponse(order.getOrderId(), order.getOrderDate(), order.getShipDate(), order.getUser().getUserId(), order.getAddress().getZipcode(), order.getAddress().getAddressDetail(), orderedProducts);
        return Optional.of(orderResponse);
    }

    @Override
    public void createOrder(OrderRequest orderRequest) throws ProductNotFoundException {
        User user = userRepository.findById(orderRequest.getUserId()).orElse(null);
        Address address = addressRepository.findById(orderRequest.getAddressId()).orElse(null);

        if (user == null || address == null) {
            throw new IllegalStateException("user 또는 address가 null 입니다.");
        }

        Order order = Order.builder()
                .user(user)
                .address(address)
                .orderDate(LocalDateTime.now())
                .shipDate(LocalDateTime.now().plusDays(3))
                .build();

        List<OrderDetail> orderDetails = new ArrayList<>();

        for (OrderedProductDto productDto : orderRequest.getOrderProducts()) {
            Product product = productRepository.findById(productDto.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException(productDto.getProductId()));

            OrderDetail orderDetail = OrderDetail.builder()
                        .order(order)
                        .product(product)
                        .unitCost(productDto.getUnitCost())
                        .quantity(productDto.getQuantity())
                        .build();

            orderDetails.add(orderDetail);
        }

        orderRepository.save(order);
        orderDetailsRepository.saveAll(orderDetails);
    }

    @Override
    public void deleteOrder(Integer orderId) throws OrderNotFoundException {

        if(orderRepository.findById(orderId).isPresent()){
            orderRepository.deleteById(orderId);
        } else {
            throw new OrderNotFoundException(orderId);
        }

    }
}
