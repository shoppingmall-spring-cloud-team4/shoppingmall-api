package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.domain.ReviewDto;
import com.nhnacademy.shoppingmall.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<ReviewDto> getAllByUser_UserId(Integer userId); // 특정 User가 작성한 모든 reviews
    List<ReviewDto> getAllByProduct_ProductId(Integer productId); // 특정 product에 작성된 모든 reviews
}
