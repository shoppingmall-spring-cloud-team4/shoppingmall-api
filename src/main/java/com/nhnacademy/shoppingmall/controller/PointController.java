package com.nhnacademy.shoppingmall.controller;

import com.nhnacademy.shoppingmall.domain.PointRegisterRequest;
import com.nhnacademy.shoppingmall.entity.Point;
import com.nhnacademy.shoppingmall.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shop/points")
@RequiredArgsConstructor
public class PointController {

    private final PointService pointService;

    @GetMapping
    public ResponseEntity<Point> getPoint(@RequestHeader("X-USER-ID") String userId) {
        Point point = pointService.getPoint(userId);
        return ResponseEntity.ok(point);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createPoint(@RequestHeader("X-USER-ID") String userId) {
        pointService.createPoints(userId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updatePoints(@RequestHeader("X-USER-ID") String userId,
                                           @RequestBody PointRegisterRequest pointRegisterRequest) {
        pointService.updatePoints(userId, pointRegisterRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}