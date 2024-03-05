package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, ProductCategory.Pk> {

}
