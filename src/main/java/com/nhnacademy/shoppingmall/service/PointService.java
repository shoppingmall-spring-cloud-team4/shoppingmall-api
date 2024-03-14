package com.nhnacademy.shoppingmall.service;

import com.nhnacademy.shoppingmall.domain.PointRegisterRequest;
import com.nhnacademy.shoppingmall.entity.Point;

public interface PointService {
    Point getAllPointHistory(String userId);
    Point getPoint(String userId);
    void createPoints(String userId, PointRegisterRequest pointRegisterRequest);

    void earnPoints(Point point);

    void deductPoints(String userId, PointRegisterRequest pointRegisterRequest);
}
