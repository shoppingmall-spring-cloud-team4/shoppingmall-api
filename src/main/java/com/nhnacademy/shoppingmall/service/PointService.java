package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.PointRegisterRequest;
import com.nhnacademy.shoppingmall.entity.Point;

public interface PointService {
    Point getPoint(String userId);
    void createPoints(String userId);
    void updatePoints(String userId, PointRegisterRequest pointRegisterRequest);
}
