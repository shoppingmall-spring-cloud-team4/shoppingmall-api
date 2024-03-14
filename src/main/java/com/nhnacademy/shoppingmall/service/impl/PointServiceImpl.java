package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.domain.PointRegisterRequest;
import com.nhnacademy.shoppingmall.entity.Point;
import com.nhnacademy.shoppingmall.entity.User;
import com.nhnacademy.shoppingmall.exception.UserPointNotEnoughException;
import com.nhnacademy.shoppingmall.repository.PointRepository;
import com.nhnacademy.shoppingmall.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;

    @Override
    public Point getAllPointHistory(String userId) {
        return pointRepository.findByUser_UserId(userId).orElse(null);
    }

    @Override
    public Point getPoint(String userId){
        return pointRepository.findFirstByUser_UserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public void createPoints(String userId, PointRegisterRequest pointRegisterRequest) {
        Point point = Point.builder()
                .points(pointRegisterRequest.getPoints())
                .pointHistory(pointRegisterRequest.getPointHistory())
                .user(User.builder()
                        .userId(userId)
                        .build())
                .build();

        pointRepository.save(point);
    }

    @Async
    @Override
    public void earnPoints(Point point) {
//        Point point = pointRepository.findFirstByUser_UserIdOrderByCreatedAtDesc(userId);
//        point = Point.builder()
//                .points(point.getPoints() + pointRegisterRequest.getPoints())
//                .pointHistory(pointRegisterRequest.getPointHistory())
//                .user(point.getUser())
//                .build();
//        p

        pointRepository.save(point);
        log.info(">>>>point적립");
    }

    @Override
    public void deductPoints(String userId, PointRegisterRequest pointRegisterRequest) {
        Point point = pointRepository.findFirstByUser_UserIdOrderByCreatedAtDesc(userId);
        if (point.getPoints() < pointRegisterRequest.getPoints()) {
            throw new UserPointNotEnoughException(point.getPoints());
        }

        point = Point.builder()
                .points(point.getPoints() - pointRegisterRequest.getPoints())
                .pointHistory(pointRegisterRequest.getPointHistory())
                .user(point.getUser())
                .build();
        pointRepository.save(point);
        log.info(">>>>point차감");
    }


}
