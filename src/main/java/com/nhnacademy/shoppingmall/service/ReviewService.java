package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.ReviewDto;
import com.nhnacademy.shoppingmall.domain.ReviewRegisterDto;
import com.nhnacademy.shoppingmall.domain.ReviewUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getReviewsByProductId(Integer productId); // 특정 product에 작성된 모든 reviews
    Page<ReviewDto> getPagesByProductId(Integer productId, Pageable pageable);
    List<ReviewDto> getReviewsByUserId(String userId); // 특정 user가 작성한 모든 reviews
    Page<ReviewDto> getPagesByUserId(String userId, Pageable pageable);
    void createReview(ReviewRegisterDto reviewRegisterDto, Integer productId);
    void updateReview(ReviewUpdateDto reviewUpdateDto, Integer reviewId);
    void deleteReview(Integer reviewId);
}