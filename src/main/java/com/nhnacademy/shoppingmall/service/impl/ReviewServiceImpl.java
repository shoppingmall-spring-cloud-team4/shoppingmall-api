package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.domain.ReviewDto;
import com.nhnacademy.shoppingmall.domain.ReviewRegisterDto;
import com.nhnacademy.shoppingmall.entity.Review;
import com.nhnacademy.shoppingmall.repository.ReviewRepository;
import com.nhnacademy.shoppingmall.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDto> getReviewsByProductId(Integer productId) {
        return reviewRepository.getAllByProduct_ProductId(productId);
    }

    @Override
    public List<ReviewDto> getReviewsByUserId(String userId) {
        return reviewRepository.getAllByUser_UserId(userId);
    }

    @Override
    public void createReview(ReviewRegisterDto reviewRegisterDto) {
        Review review = Review.builder().reviewDateCreated(LocalDateTime.now()).build();
        BeanUtils.copyProperties(reviewRegisterDto, review);

        reviewRepository.save(review);
    }

    @Override
    public void updateReview(ReviewRegisterDto reviewRegisterDto, Integer reviewId) {
        Review existingReview = reviewRepository.findById(reviewId).orElse(null);

        if (existingReview != null) {
            BeanUtils.copyProperties(reviewRegisterDto, existingReview);
            reviewRepository.save(existingReview);
        }
    }

    @Override
    public void deleteReview(Integer reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
