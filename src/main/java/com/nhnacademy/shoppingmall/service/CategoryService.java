package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.CategoryRequest;
import com.nhnacademy.shoppingmall.domain.CategoryResponse;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryRequest> getAllCategories();
    Optional<CategoryRequest> getCategory(String CategoryName);
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse updateCategory(CategoryRequest categoryRequest);
    void deleteCategory(Integer categoryId);
}
