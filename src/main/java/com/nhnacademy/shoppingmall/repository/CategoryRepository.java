package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.domain.CategoryResponse;
import com.nhnacademy.shoppingmall.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "select new com.nhnacademy.shoppingmall.domain.CategoryResponse(c.categoryId, c.categoryName) from Category c")
    List<CategoryResponse> findAllBy();

    Optional<CategoryResponse> findByCategoryName(String categoryName);

}
