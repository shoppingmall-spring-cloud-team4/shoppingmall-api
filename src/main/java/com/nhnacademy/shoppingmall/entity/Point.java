package com.nhnacademy.shoppingmall.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Points")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private Integer pointId;

    private Integer points; // 포인트

    @Column(name = "point_history")
    @Length(max = 100)
    private String pointHistory; // 포인트 내역

    @Column(name = "created_at")
    private LocalDateTime createdAt; // 생성일

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Builder
    public Point(Integer points, String pointHistory, User user) {
        this.points = points;
        this.pointHistory = pointHistory;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }
}
