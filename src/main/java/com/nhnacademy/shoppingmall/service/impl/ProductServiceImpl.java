package com.nhnacademy.shoppingmall.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.shoppingmall.domain.ProductDto;
import com.nhnacademy.shoppingmall.domain.ProductRegisterDto;
import com.nhnacademy.shoppingmall.entity.Product;
import com.nhnacademy.shoppingmall.exception.ProductNotFoundException;
import com.nhnacademy.shoppingmall.repository.ProductRepository;
import com.nhnacademy.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final int MAX_RECENT_PRODUCTS_SIZE = 5;
    private static final String RECENT_PRODUCTS = "recentProducts";

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getProducts() {
        return productRepository.getAllBy();
    }

    @Override
    public Page<ProductDto> getAllProductsPage(Pageable pageable)
    {
        return productRepository.getPageBy(pageable);
    }

    @Override
    public Optional<ProductDto> getProductById(Integer productId) {
        if(!productRepository.existsById(productId))
            throw new ProductNotFoundException(productId);

        return productRepository.getByProductId(productId);
    }

    @Override
    public void saveRecentProductToCookie(Integer productId, HttpServletResponse response, HttpServletRequest request) throws IOException {
        List<ProductDto> recentProducts = getRecentProductsFromCookie(request);
        Product p = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));

        recentProducts.removeIf(productDto -> productDto.getProductId().equals(productId));
        recentProducts.add(0, new ProductDto(productId, p.getProductImage(), p.getDescription(), p.getModelName(), p.getUnitCost()));

        if (recentProducts.size() > MAX_RECENT_PRODUCTS_SIZE)
            recentProducts.remove(recentProducts.size() - 1); // 최근 본 상품의 갯수를 5개로 유지.

        String recentProductsJson = objectMapper.writeValueAsString(recentProducts);
        String encodedRecentProductsJson = URLEncoder.encode(recentProductsJson, StandardCharsets.UTF_8); //
        // List<ProductDto>를 문자열로 encoding 하여 Cookie에 저장.

        Cookie cookie = new Cookie(RECENT_PRODUCTS, encodedRecentProductsJson);
        cookie.setMaxAge(24 * 60 * 60); // (1일)
        response.addCookie(cookie);
    }

    @Override
    public List<ProductDto> getRecentProductsFromCookie(HttpServletRequest request) throws IOException {
        Cookie[] cookies = request.getCookies();
        String recentProductCookieValue = getCookieValue(cookies);

        if (recentProductCookieValue != null) {
            String decodedCookieValue = URLDecoder.decode(recentProductCookieValue, StandardCharsets.UTF_8);
            return objectMapper.readValue(decodedCookieValue, new TypeReference<List<ProductDto>>() {});
        }
        return new ArrayList<>();
    }

    private String getCookieValue(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(RECENT_PRODUCTS)) {
                    return cookie.getValue();
                }
            }
        }

        return null; // null을 반환하면 새로운 ArrayList<ProductDto>가 반환된다.
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
