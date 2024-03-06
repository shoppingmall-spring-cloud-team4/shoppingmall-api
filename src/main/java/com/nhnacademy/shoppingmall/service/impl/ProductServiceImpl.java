package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.domain.ProductDto;
import com.nhnacademy.shoppingmall.domain.ProductRegisterDto;
import com.nhnacademy.shoppingmall.entity.Product;
import com.nhnacademy.shoppingmall.repository.ProductRepository;
import com.nhnacademy.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getProducts() {
        return productRepository.getAll();
    }

    @Override
    public List<ProductDto> getProductsByCategoryId(Integer categoryId) {
        return productRepository.getAllByCategory_CategoryId(categoryId);
    }

    @Override
    public Optional<ProductDto> getProductById(Integer productId) {
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
        Product existedProduct = productRepository.findById(productId).orElse(null);

        if (existedProduct != null) {
            BeanUtils.copyProperties(productRegisterDto, existedProduct);
            productRepository.save(existedProduct);
        }
    }

    @Override
    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }
}
