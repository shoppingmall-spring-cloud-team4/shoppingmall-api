package com.nhnacademy.shoppingmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.shoppingmall.domain.ProductCategoryDto;
import com.nhnacademy.shoppingmall.domain.ProductCategoryRegisterDto;
import com.nhnacademy.shoppingmall.service.ProductCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductCategoryController.class)
class ProductCategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ProductCategoryService productCategoryService;

    @Test
    void getProductCategoriesByProductId() throws Exception {
        Integer productId = 1;
        List<ProductCategoryDto> expectedProductCategories = new ArrayList<>();
        expectedProductCategories.add(new ProductCategoryDto(productId, 1));
        expectedProductCategories.add(new ProductCategoryDto(productId, 2));

        when(productCategoryService.getProductCategoriesByProductId(productId)).thenReturn(expectedProductCategories);

        mockMvc.perform(get("/api/product/category/{productId}", productId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedProductCategories)));

        verify(productCategoryService, times(1)).getProductCategoriesByProductId(productId);
    }

    @Test
    void createProductCategory() throws Exception {
        Integer productId = 1;
        ProductCategoryRegisterDto productCategoryRegisterDto = new ProductCategoryRegisterDto(1);

        mockMvc.perform(post("/api/product/category/{productId}", productId)
                        .content(objectMapper.writeValueAsString(productCategoryRegisterDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        verify(productCategoryService, times(1)).createProductCategory(productId, productCategoryRegisterDto);
    }

    @Test
    void deleteProductCategory() throws Exception {
        Integer productId = 1;
        Integer categoryId = 1;

        mockMvc.perform(delete("/api/product/category/{productId}/{categoryId}", productId, categoryId))
                .andExpect(status().isNoContent());

        verify(productCategoryService, times(1)).deleteProductCategory(productId, categoryId);
    }
}