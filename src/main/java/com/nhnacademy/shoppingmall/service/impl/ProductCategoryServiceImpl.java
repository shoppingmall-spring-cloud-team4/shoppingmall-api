package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.domain.ProductCategoryDto;
import com.nhnacademy.shoppingmall.domain.ProductCategoryRegisterDto;
import com.nhnacademy.shoppingmall.entity.Category;
import com.nhnacademy.shoppingmall.entity.Product;
import com.nhnacademy.shoppingmall.entity.ProductCategory;
import com.nhnacademy.shoppingmall.exception.CategoryNotFoundException;
import com.nhnacademy.shoppingmall.exception.ProductNotFoundException;
import com.nhnacademy.shoppingmall.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.repository.ProductCategoryRepository;
import com.nhnacademy.shoppingmall.repository.ProductRepository;
import com.nhnacademy.shoppingmall.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public ProductCategory createProductCategory(Integer productId, ProductCategoryRegisterDto productCategoryRegisterDto) {
        Integer categoryId = productCategoryRegisterDto.getCategoryId();

        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));

        ProductCategory.Pk pk = new ProductCategory.Pk(categoryId, productId);

        ProductCategory productCategory = ProductCategory.builder()
                .pk(pk)
                .product(product)
                .category(category)
                .build();

        return productCategoryRepository.save(productCategory);
    }

    @Override
    public void deleteProductCategory(Integer productId, Integer categoryId) {
        ProductCategory.Pk pk = new ProductCategory.Pk(categoryId, productId);
        productCategoryRepository.deleteById(pk);
    }


    @Override
    public List<ProductCategoryDto> getProductCategoriesByProductId(Integer productId) {
        return productCategoryRepository.getAllByProductProductId(productId);
    }
}
