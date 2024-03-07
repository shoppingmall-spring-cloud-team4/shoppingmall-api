package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.CategoryRequest;
import com.nhnacademy.shoppingmall.domain.CategoryResponse;
import com.nhnacademy.shoppingmall.exception.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategory(String categoryName);

    void createCategory(CategoryRequest categoryRequest);

    void updateCategory(Integer categoryId, CategoryRequest categoryRequest);

    void deleteCategory(Integer categoryId) throws CategoryNotFoundException;
}
