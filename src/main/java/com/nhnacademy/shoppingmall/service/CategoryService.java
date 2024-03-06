package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.CategoryRequest;
import com.nhnacademy.shoppingmall.domain.CategoryResponse;
import com.nhnacademy.shoppingmall.exception.CategoryNotFoundException;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryResponse> getAllCategories();

    Optional<CategoryResponse> getCategory(String CategoryName);

    void createCategory(CategoryRequest categoryRequest);

    void updateCategory(Integer CategoryId, CategoryRequest categoryRequest) throws CategoryNotFoundException;

    void deleteCategory(Integer categoryId) throws CategoryNotFoundException;
}
