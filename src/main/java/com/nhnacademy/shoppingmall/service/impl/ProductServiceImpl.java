package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.domain.ProductDto;
import com.nhnacademy.shoppingmall.domain.ProductRegisterDto;
import com.nhnacademy.shoppingmall.entity.Product;
import com.nhnacademy.shoppingmall.exception.ProductNotFoundException;
import com.nhnacademy.shoppingmall.repository.ProductRepository;
import com.nhnacademy.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getProducts() {
        return productRepository.getAllBy();
    }

    @Override
    public Optional<ProductDto> getProductById( Integer productId) {
        if(!productRepository.existsById(productId))
            throw new ProductNotFoundException(productId);

        return productRepository.getByProductId(productId);
    }

    @Override
    public void createProduct(ProductRegisterDto productRegisterDto) {
        Product product = Product.builder()
                .productId(productRegisterDto.getProductId())
                .modelNumber(productRegisterDto.getModelNumber())
                .modelName(productRegisterDto.getModelName())
                .productImage(productRegisterDto.getProductImage())
                .description(productRegisterDto.getDescription())
                .unitCost(productRegisterDto.getUnitCost())
                .build();

        productRepository.save(product);
    }

    @Override
    public void updateProduct(ProductRegisterDto productRegisterDto, Integer productId) {
        if (productRepository.existsById(productId)) {
            Product product = Product.builder()
                            .productId(productRegisterDto.getProductId())
                            .modelName(productRegisterDto.getModelName())
                            .modelNumber(productRegisterDto.getModelNumber())
                            .productImage(productRegisterDto.getProductImage())
                            .unitCost(productRegisterDto.getUnitCost())
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
