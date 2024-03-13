package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.domain.ReviewDto;
import com.nhnacademy.shoppingmall.domain.ReviewRegisterDto;
import com.nhnacademy.shoppingmall.domain.ReviewUpdateDto;
import com.nhnacademy.shoppingmall.entity.Product;
import com.nhnacademy.shoppingmall.entity.Review;
import com.nhnacademy.shoppingmall.entity.User;
import com.nhnacademy.shoppingmall.exception.AlreadyExistReviewException;
import com.nhnacademy.shoppingmall.exception.ProductNotFoundException;
import com.nhnacademy.shoppingmall.exception.ReviewNotFoundException;
import com.nhnacademy.shoppingmall.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.repository.ProductRepository;
import com.nhnacademy.shoppingmall.repository.ReviewRepository;
import com.nhnacademy.shoppingmall.repository.UserRepository;
import com.nhnacademy.shoppingmall.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public List<ReviewDto> getReviewsByProductId(Integer productId) {
        return reviewRepository.getAllByProduct_ProductId(productId);
    }

    @Override
    public Page<ReviewDto> getPagesByProductId(Integer productId, Pageable pageable) {
        return reviewRepository.getPagesByProduct_ProductId(productId, pageable);
    }

    @Override
    public List<ReviewDto> getReviewsByUserId(String userId) {
        return reviewRepository.getAllByUser_UserId(userId);
    }

    @Override
    public Page<ReviewDto> getPagesByUserId(String userId, Pageable pageable) {
        return reviewRepository.getPagesByUser_UserId(userId, pageable);
    }

    @Override
    public void createReview(ReviewRegisterDto reviewRegisterDto, Integer productId) {
        String userId = reviewRegisterDto.getUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        if(Objects.nonNull(reviewRepository.getByUser_UserIdAndProduct_ProductId(userId, productId)))
            throw new AlreadyExistReviewException(userId);

        Review review = Review.builder()
                .reviewDateCreated(LocalDateTime.now())
                .comment(reviewRegisterDto.getComment())
                .user(user)
                .rating(reviewRegisterDto.getRating())
                .comment(reviewRegisterDto.getComment())
                .product(product)
                .build();
        BeanUtils.copyProperties(reviewRegisterDto, review);

        reviewRepository.save(review);
    }

    @Override
    public void updateReview(ReviewUpdateDto reviewUpdateDto, Integer reviewId) {
        Review existingReview = reviewRepository.findById(reviewId).orElse(null);

        if (existingReview != null) {
            Review review = Review.builder()
                    .reviewId(reviewId)
                    .reviewDateCreated(LocalDateTime.now())
                    .comment(reviewUpdateDto.getComment())
                    .user(existingReview.getUser())
                    .rating(reviewUpdateDto.getRating())
                    .comment(reviewUpdateDto.getComment())
                    .product(existingReview.getProduct())
                    .build();
            reviewRepository.save(review);
        } else {
            throw new ReviewNotFoundException(reviewId);
        }
    }

    @Override
    public void deleteReview(Integer reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
