package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.domain.CategoryRequest;
import com.nhnacademy.shoppingmall.domain.CategoryResponse;
import com.nhnacademy.shoppingmall.service.CategoryService;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<CategoryRequest> getAllCategories() {
        return null;
    }

    @Override
    public Optional<CategoryRequest> getCategory(String CategoryName) {
        return Optional.empty();
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        return null;
    }

    @Override
    public CategoryResponse updateCategory(CategoryRequest categoryRequest) {
        return null;
    }

    @Override
    public void deleteCategory(Integer categoryId) {

    }
}
