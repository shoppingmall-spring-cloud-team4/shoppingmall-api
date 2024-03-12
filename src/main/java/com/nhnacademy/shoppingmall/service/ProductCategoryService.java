package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.ProductCategoryDto;
import com.nhnacademy.shoppingmall.domain.ProductCategoryRegisterDto;
import com.nhnacademy.shoppingmall.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory createProductCategory(Integer productId, ProductCategoryRegisterDto productCategoryRegisterDto);
    void deleteProductCategory(Integer productId, Integer categoryId);
    List<ProductCategoryDto> getProductCategoriesByProductId(Integer productId);

}
