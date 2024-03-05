package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
