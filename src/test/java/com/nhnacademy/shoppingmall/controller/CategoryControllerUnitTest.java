package com.nhnacademy.shoppingmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.shoppingmall.domain.CategoryRequest;
import com.nhnacademy.shoppingmall.domain.CategoryResponse;
import com.nhnacademy.shoppingmall.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private CategoryService categoryService;

    @Test
    void getAllCategories() throws Exception {
        CategoryResponse categoryResponse = new CategoryResponse(100, "모니터");
        List<CategoryResponse> categoryResponses = Collections.singletonList(categoryResponse);

        given(categoryService.getAllCategories()).willReturn(categoryResponses);

        mockMvc.perform(get("/api/category"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].categoryId", equalTo(100)))
                .andExpect(jsonPath("$[0].categoryName", equalTo("모니터")));
    }

    @Test
    void getCategory() throws Exception {
        CategoryResponse categoryResponse = new CategoryResponse(101, "태블릿");

        given(categoryService.getCategory("Electronics")).willReturn(categoryResponse);

        mockMvc.perform(get("/api/category/{categoryName}", "Electronics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryId", equalTo(101)))
                .andExpect(jsonPath("$.categoryName", equalTo("태블릿")));
    }

    @Test
    void createCategory() throws Exception {
        CategoryRequest categoryRequest = new CategoryRequest("책");

        mockMvc.perform(post("/api/category")
                        .content(objectMapper.writeValueAsString(categoryRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateCategory() throws Exception{
        CategoryRequest categoryRequest = new CategoryRequest("워치");

        mockMvc.perform(put("/api/category/{categoryId}", 1)
                        .content(objectMapper.writeValueAsString(categoryRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCategory() throws Exception {
        mockMvc.perform(delete("/api/category/{categoryId}", 1))
                .andExpect(status().isOk());
    }
}