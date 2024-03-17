package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.entity.Point;
import com.nhnacademy.shoppingmall.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, String> {
    Point findByUser(User user);
//    Optional<Point> findByUser_UserId(String userId);

//    Point findFirstByUser_UserIdOrderByCreatedAtDesc(String userId);
}
