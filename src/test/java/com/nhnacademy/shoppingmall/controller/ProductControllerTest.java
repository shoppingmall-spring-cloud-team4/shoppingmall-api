package com.nhnacademy.shoppingmall.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.nhnacademy.shoppingmall.domain.ProductDto;
import com.nhnacademy.shoppingmall.domain.ProductRegisterDto;
import com.nhnacademy.shoppingmall.service.ProductService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;


    @Test
    void getAllProductsPage() throws Exception {
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);
        List<ProductDto> expectedProducts = new ArrayList<>(
                Arrays.asList(
                        new ProductDto(1, "productImage", "description1", "name", 10000),
                        new ProductDto(2, "productImage", "description1", "name", 10000)
                ));
        Page<ProductDto> productDtoPage = new PageImpl<>(expectedProducts, pageable, expectedProducts.size());

        when(productService.getAllProductsPage(pageable)).thenReturn(productDtoPage);

        mockMvc.perform(get("/api/product?page=" + page + "&size=" + size))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedProducts)));

        verify(productService, times(1)).getAllProductsPage(pageable);
    }

    @Test
    void recentlyViewedPage() throws Exception {
        // Given
        List<ProductDto> expectedProducts = new ArrayList<>(
                Arrays.asList(  new ProductDto(1, "productImage", "description1", "name", 10000),
                        new ProductDto(2, "productImage", "description1", "name", 10000))
        );

        // Mock the behavior of productService.
        when(productService.getRecentProductsFromCookie(any(HttpServletRequest.class))).thenReturn(expectedProducts);

        // When & Then
        mockMvc.perform(get("/api/product/recentProducts"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedProducts)));

        verify(productService, times(1)).getRecentProductsFromCookie(any(HttpServletRequest.class));
    }

    @Test
    void getProduct() throws Exception{
        Integer productId = 1;
        ProductDto productDto = new ProductDto(1, "image", "description", "model", 10000);

        when(productService.getProductById(productId)).thenReturn(Optional.of(productDto));

        mockMvc.perform(get("/api/product/" + productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelName", is(productDto.getModelName())));

        verify(productService, times(1)).getProductById(productId);
    }

    @Test
    void createProduct() throws Exception {
        ProductRegisterDto productRegisterDto = new ProductRegisterDto(1, "model1", "model1", "image1", 10000, "description1");

        doNothing().when(productService).createProduct(productRegisterDto);

        mockMvc.perform(post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productRegisterDto)))
                .andExpect(status().isCreated());

        verify(productService, times(1)).createProduct(productRegisterDto);
    }

    @Test
    void updateProduct() throws Exception {
        Integer productId = 1;
        ProductRegisterDto productRegisterDto = new ProductRegisterDto(1, "model1", "model1", "image1", 10000, "description1");

        doNothing().when(productService).updateProduct(productRegisterDto, productId);

        mockMvc.perform(put("/api/product/" + productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productRegisterDto)))
                .andExpect(status().isOk());

        verify(productService, times(1)).updateProduct(productRegisterDto, productId);
    }

    @Test
    void deleteProduct() throws Exception{
        Integer productId = 1;

        doNothing().when(productService).deleteProduct(productId);

        mockMvc.perform(delete("/api/product/" + productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(productService, times(1)).deleteProduct(productId);
    }
}