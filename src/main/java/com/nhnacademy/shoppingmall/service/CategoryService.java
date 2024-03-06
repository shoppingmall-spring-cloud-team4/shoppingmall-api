package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.CategoryRequest;
import com.nhnacademy.shoppingmall.domain.CategoryResponse;
import com.nhnacademy.shoppingmall.exception.CategoryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public interface CategoryService {

    List<CategoryResponse> getAllCategories();

    CategoryResponse getCategory(String CategoryName);

    void createCategory(CategoryRequest categoryRequest);

    void updateCategory(Integer CategoryId, CategoryRequest categoryRequest);

    void deleteCategory(Integer categoryId) throws CategoryNotFoundException;
}
