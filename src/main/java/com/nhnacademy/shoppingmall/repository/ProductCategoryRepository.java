package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.domain.ProductCategoryDto;
import com.nhnacademy.shoppingmall.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, ProductCategory.Pk> {

    @Query("select new com.nhnacademy.shoppingmall.domain.ProductCategoryDto(pc.pk.productId, pc.pk.categoryId) from ProductCategory pc WHERE pc.pk.productId = :productId")
    List<ProductCategoryDto> getAllByProductProductId(@Param("productId") Integer productId);
}
