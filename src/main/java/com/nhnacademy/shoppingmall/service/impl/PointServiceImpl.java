package com.nhnacademy.shoppingmall.service.impl;

import com.nhnacademy.shoppingmall.domain.PointRegisterRequest;
import com.nhnacademy.shoppingmall.entity.Point;
import com.nhnacademy.shoppingmall.entity.PointHistory;
import com.nhnacademy.shoppingmall.entity.User;
import com.nhnacademy.shoppingmall.exception.DuplicateUserIdException;
import com.nhnacademy.shoppingmall.exception.UserPointNotEnoughException;
import com.nhnacademy.shoppingmall.repository.PointHistoryRepository;
import com.nhnacademy.shoppingmall.repository.PointRepository;
import com.nhnacademy.shoppingmall.repository.UserRepository;
import com.nhnacademy.shoppingmall.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private final UserRepository userRepository;

    @Override
    public Point getPoint(String userId) {
        User user = userRepository.getReferenceById(userId);

        return pointRepository.findByUser(user);
    }

    @Override
    public void createPoints(String userId) {
        if(pointRepository.findById(userId).isPresent()){
            throw new DuplicateUserIdException(userId);
        }

        Point point = Point.builder()
                           .userId(userId)
                           .point(100000)
                           .build();
        pointRepository.save(point);

        PointHistory pointHistory = PointHistory.builder()
                                                .pointHistory("회원가입")
                                                .point(point)
                                                .build();
        pointHistoryRepository.save(pointHistory);
    }

    @Override
    public void updatePoints(String userId, PointRegisterRequest pointRegisterRequest) {
        User user = userRepository.getReferenceById(userId);
        Point point = pointRepository.findByUser(user);

        point = Point.builder()
                     .userId(userId)
                     .point(point.getPoint() + pointRegisterRequest.getPoints())
                     .build();

        Integer currentPoint = point.getPoint();
        if(currentPoint < 0){
            throw new UserPointNotEnoughException(currentPoint);
        }
        pointRepository.saveAndFlush(point);

        PointHistory pointHistory = PointHistory.builder()
                                                .pointHistory(pointRegisterRequest.getPointHistory())
                                                .point(point)
                                                .build();
        pointHistoryRepository.saveAndFlush(pointHistory);
        log.info(">>>>point적립");
    }
}
