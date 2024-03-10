package com.nhnacademy.shoppingmall.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.nhnacademy.shoppingmall.domain.ProductDto;
import com.nhnacademy.shoppingmall.domain.ProductRegisterDto;
import com.nhnacademy.shoppingmall.service.ProductService;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Test
    void getProducts() throws Exception {
        List<ProductDto> productDtos = Arrays.asList(
                new ProductDto("image1", "description1", "model1", 18000L),
                new ProductDto("image2", "description2", "model2", 20000L)
        );

        when(productService.getProducts()).thenReturn(productDtos);

        mockMvc.perform(get("/api/product")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(productDtos.size())))
                .andExpect(jsonPath("$[0].modelName", is(productDtos.get(0).getModelName())))
                .andExpect(jsonPath("$[1].modelName", is(productDtos.get(1).getModelName())));

        verify(productService, times(1)).getProducts();
    }

    @Test
    void getProduct() throws Exception{
        Integer productId = 1;
        ProductDto productDto = new ProductDto("image", "description", "model", 10000L);

        when(productService.getProductById(productId)).thenReturn(Optional.of(productDto));

        mockMvc.perform(get("/api/product/" + productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modelName", is(productDto.getModelName())));

        verify(productService, times(1)).getProductById(productId);
    }

    @Test
    void createProduct() throws Exception {
        ProductRegisterDto productRegisterDto = new ProductRegisterDto(1, "model1", "model1", "image1", 10000L, "description1");

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
        ProductRegisterDto productRegisterDto = new ProductRegisterDto(1, "model1", "model1", "image1", 10000L, "description1");

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