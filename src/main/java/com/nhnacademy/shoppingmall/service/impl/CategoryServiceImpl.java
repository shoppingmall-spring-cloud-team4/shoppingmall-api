package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.domain.CategoryRequest;
import com.nhnacademy.shoppingmall.domain.CategoryResponse;
import com.nhnacademy.shoppingmall.entity.Category;
import com.nhnacademy.shoppingmall.exception.CategoryNotFoundException;
import com.nhnacademy.shoppingmall.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAllBy();
    }

    @Override
    public CategoryResponse getCategory(String categoryName){
        return categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException(null));
    }

    @Override
    public void createCategory(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .build();
        BeanUtils.copyProperties(categoryRequest, category);

        categoryRepository.save(category);

    }

    @Override
    public void updateCategory(Integer categoryId, CategoryRequest categoryRequest){
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if(optionalCategory.isPresent()){
            Category updatedCategory = Category.builder()
                    .categoryId(categoryId)
                    .categoryName(categoryRequest.getCategoryName())
                    .build();

            categoryRepository.save(updatedCategory);
        } else {
            throw new CategoryNotFoundException(categoryId);
        }
    }

    @Override
    public void deleteCategory(Integer categoryId) throws CategoryNotFoundException {
        if(categoryRepository.findById(categoryId).isPresent()){
            categoryRepository.deleteById(categoryId);
        } else {
            throw new CategoryNotFoundException(categoryId);
        }
    }
}
