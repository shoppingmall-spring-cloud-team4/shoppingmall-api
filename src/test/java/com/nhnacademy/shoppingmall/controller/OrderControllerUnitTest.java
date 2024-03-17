package com.nhnacademy.shoppingmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.shoppingmall.domain.OrderRequest;
import com.nhnacademy.shoppingmall.domain.OrderResponse;
import com.nhnacademy.shoppingmall.domain.OrderedProductDto;
import com.nhnacademy.shoppingmall.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerUnitTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    OrderService orderService;

    @Test
    void getAllOrders() throws Exception {
        OrderedProductDto orderedProductDto = new OrderedProductDto(1, 3, 2000);
        List<OrderedProductDto> orderProducts = Collections.singletonList(orderedProductDto);

        OrderResponse orderResponse = new OrderResponse(100, LocalDateTime.now(), LocalDateTime.now().plusDays(3), "aaa", "123대로", "a빌딩", orderProducts, 10000);
        List<OrderResponse> orderResponses = Collections.singletonList(orderResponse);

        given(orderService.getAllOrders("aaa"))
                .willReturn(orderResponses);

        mockMvc.perform(get("/api/shop/order")
                        .header("X-USER-ID", "aaa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].zipcode", equalTo("123대로")))
                .andExpect(jsonPath("$[0].addressDetail", equalTo("a빌딩")));

    }

    @Test
    void getOrder() throws Exception {
        OrderedProductDto orderedProductDto = new OrderedProductDto(1, 3, 2000);
        List<OrderedProductDto> orderProducts = Collections.singletonList(orderedProductDto);
        OrderResponse orderResponse = new OrderResponse(100, LocalDateTime.now(), LocalDateTime.now().plusDays(3), "aaa", "123대로", "a빌딩", orderProducts, 10000);

        given(orderService.getOrder(100))
                .willReturn(orderResponse);

        mockMvc.perform(get("/api/shop/order/{orderId}", 100))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderedProducts").isNotEmpty())
                .andExpect(jsonPath("$.orderedProducts[0].quantity").value(3));
    }

    @Test
    void createOrder() throws Exception{

        OrderedProductDto orderedProductDto = new OrderedProductDto(3, 3, 5000);
        List<OrderedProductDto> orderProducts = Collections.singletonList(orderedProductDto);
        OrderRequest orderRequest = new OrderRequest("aaa", 1, orderProducts);

        mockMvc.perform(post("/api/shop/order")
                        .header("X-USER-ID", "aaa")
                        .content(objectMapper.writeValueAsString(orderRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    void deleteOrder() throws Exception {
        mockMvc.perform(delete("/api/shop/order/{orderId}", 100))
                .andExpect(status().isOk());
    }
}