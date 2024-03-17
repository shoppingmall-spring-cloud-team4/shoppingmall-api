package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.domain.OrderedProductDto;
import com.nhnacademy.shoppingmall.domain.OrderRequest;
import com.nhnacademy.shoppingmall.domain.OrderResponse;
import com.nhnacademy.shoppingmall.domain.PointRegisterRequest;
import com.nhnacademy.shoppingmall.entity.*;
import com.nhnacademy.shoppingmall.exception.AddressNotFoundException;
import com.nhnacademy.shoppingmall.exception.OrderNotFoundException;
import com.nhnacademy.shoppingmall.exception.ProductNotFoundException;
import com.nhnacademy.shoppingmall.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.repository.*;
import com.nhnacademy.shoppingmall.service.OrderService;
import com.nhnacademy.shoppingmall.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final UserRepository userRepository;
    private final PointService pointService;
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;

    @Override
    public List<OrderResponse> getAllOrders(String userId) {
        List<Order> orders = orderRepository.findAllByUser_UserId(userId);
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {
            List<OrderDetail> orderDetails = orderDetailsRepository.findAllByOrder_OrderId(order.getOrderId());
            List<OrderedProductDto> orderedProducts = new ArrayList<>();


            for (OrderDetail orderDetail : orderDetails) {
                OrderedProductDto orderProductDto = new OrderedProductDto(orderDetail.getProduct()
                                                                                     .getProductId(), orderDetail.getQuantity(), orderDetail.getUnitCost());
                orderedProducts.add(orderProductDto);
            }

            OrderResponse orderResponse = new OrderResponse(order.getOrderId(), order.getOrderDate(), order.getShipDate(), userId, order.getAddress()
                                                                                                                                        .getZipcode(), order.getAddress()
                                                                                                                                                            .getAddressDetail(), orderedProducts, order.getTotalPayment());
            orderResponses.add(orderResponse);
        }

        return orderResponses;
    }

    @Override
    public OrderResponse getOrder(Integer orderId) {

        Order order = orderRepository.findById(orderId)
                                     .orElseThrow(() -> new OrderNotFoundException(orderId));
        List<OrderDetail> orderDetails = orderDetailsRepository.findAllByOrder_OrderId(orderId);
        List<OrderedProductDto> orderedProducts = new ArrayList<>();

        for (OrderDetail orderDetail : orderDetails) {
            OrderedProductDto orderedProductDto = new OrderedProductDto(orderDetail.getProduct()
                                                                                   .getProductId(), orderDetail.getQuantity(), orderDetail.getUnitCost());
            orderedProducts.add(orderedProductDto);
        }

        return new OrderResponse(order.getOrderId(), order.getOrderDate(), order.getShipDate(),
                order.getUser().getUserId(), order.getAddress().getZipcode(),
                order.getAddress().getAddressDetail(), orderedProducts, order.getTotalPayment());
    }

    @Override
    public void createOrder(OrderRequest orderRequest) {
        User user = userRepository.findById(orderRequest.getUserId())
                                  .orElseThrow(() -> new UserNotFoundException(orderRequest.getUserId()));
        Address address = addressRepository.findById(orderRequest.getAddressId())
                                           .orElseThrow(() -> new AddressNotFoundException(orderRequest.getAddressId()));

        Order order = Order.builder()
                           .user(user)
                           .address(address)
                           .orderDate(LocalDateTime.now())
                           .shipDate(LocalDateTime.now().plusDays(3))
                           .build();

        List<OrderDetail> orderDetails = new ArrayList<>();
        orderRepository.save(order);

        Integer totalPayment = 0;
        for (OrderedProductDto productDto : orderRequest.getOrderProducts()) {
            Product product = productRepository.findById(productDto.getProductId())
                                               .orElseThrow(() -> new ProductNotFoundException(productDto.getProductId()));

            OrderDetail.Pk pk = new OrderDetail.Pk(order.getOrderId(), product.getProductId());
            OrderDetail orderDetail = OrderDetail.builder()
                                                 .order(order)
                                                 .product(product)
                                                 .unitCost(productDto.getUnitCost())
                                                 .quantity(productDto.getQuantity())
                                                 .pk(pk)
                                                 .build();

            totalPayment += orderDetail.getUnitCost() * orderDetail.getQuantity();

            orderDetails.add(orderDetail);
        }

        order.updateTotalCost(totalPayment);
        log.debug("총 주문 금액 : " + totalPayment);

        //결제 -> 포인트 차감
        String message = "상품 주문 : " + order.getOrderId();
        PointRegisterRequest pointRegisterRequest = new PointRegisterRequest(-totalPayment, message);
        pointService.updatePoints(user.getUserId(), pointRegisterRequest);

        //결제 포인트 10% 적립
        Integer orderPoint = (int) (totalPayment * 0.1);
        String pointHistoryMessage = "주문 시 10% 적립 : " + orderPoint;
        PointRegisterRequest orderPointRequest = new PointRegisterRequest(orderPoint, pointHistoryMessage);
        pointService.updatePoints(user.getUserId(), orderPointRequest);

    }

    @Override
    public void deleteOrder(Integer orderId) {
        if (orderRepository.findById(orderId).isPresent()) {
            orderDetailsRepository.deleteAllByOrder_OrderId(orderId);
            orderRepository.deleteById(orderId);
        } else {
            throw new OrderNotFoundException(orderId);
        }
    }
}
