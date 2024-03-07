package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.domain.ProductDto;
import com.nhnacademy.shoppingmall.domain.ProductRegisterDto;
import com.nhnacademy.shoppingmall.entity.Category;
import com.nhnacademy.shoppingmall.entity.Product;
import com.nhnacademy.shoppingmall.exception.CategoryNotFoundException;
import com.nhnacademy.shoppingmall.exception.ProductNotFoundException;
import com.nhnacademy.shoppingmall.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.repository.ProductRepository;
import com.nhnacademy.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<ProductDto> getProducts() {
        return productRepository.getAllBy();
    }

    @Override
    public List<ProductDto> getProductsByCategoryId(Integer categoryId) {
        return productRepository.getAllByCategory_CategoryId(categoryId);
    }

    @Override
    public Optional<ProductDto> getProductById(Integer productId) {
        if(!productRepository.existsById(productId))
            throw new ProductNotFoundException(productId);

        return productRepository.getByProductId(productId);
    }

    @Override
    public void createProduct(ProductRegisterDto productRegisterDto) {
        Product product = Product.builder().build();
        BeanUtils.copyProperties(productRegisterDto, product);

        productRepository.save(product);
    }

    @Override
    public void updateProduct(ProductRegisterDto productRegisterDto, Integer productId) {
        Category category = categoryRepository.findById(productRegisterDto.getCategoryId()).orElse(null);

        // 카테고리가 존재하지 않으면 업데이트 되지 않는다.
        if(category == null)
            throw new CategoryNotFoundException(productRegisterDto.getCategoryId());

        if (productRepository.existsById(productId)) {
            Product product = Product.builder()
                            .category(category)
                            .modelName(productRegisterDto.getModelName())
                            .modelNumber(productRegisterDto.getModelNumber())
                            .productImage(productRegisterDto.getProductImage())
                            .description(productRegisterDto.getDescription())
                            .build();

            productRepository.save(product);
        } else {
            throw new ProductNotFoundException(productId);
        }
    }

    @Override
    public void deleteProduct(Integer productId) {
        if(productRepository.existsById(productId))
            productRepository.deleteById(productId);
        else
            throw new ProductNotFoundException(productId);
    }
}
