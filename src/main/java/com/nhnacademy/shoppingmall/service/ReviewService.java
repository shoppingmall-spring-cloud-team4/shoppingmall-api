package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.ReviewDto;
import com.nhnacademy.shoppingmall.domain.ReviewRegisterDto;
import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<ReviewDto> getReviewsByProductId(Integer productId); // 특정 product에 작성된 모든 reviews
    List<ReviewDto> getReviewsByUserId(Integer userId); // 특정 user가 작성한 모든 reviews
    void createReview(ReviewRegisterDto reviewRegisterDto);
    void updateReview(ReviewRegisterDto reviewRegisterDto, Integer reviewId);
    void deleteReview(Integer reviewId);
}