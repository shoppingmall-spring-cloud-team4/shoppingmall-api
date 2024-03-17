package com.nhnacademy.shoppingmall.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PointHistory")
public class PointHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_history_id")
    private Integer pointHistoryId;

    @Column(name = "point_history")
    @Length(max = 100)
    private String pointHistory; // 포인트 내역

    @Column(name = "created_at")
    private LocalDateTime createdAt; // 생성일

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Point point;

    @Builder
    public PointHistory(String pointHistory, Point point) {
        this.pointHistory = pointHistory;
        this.createdAt = LocalDateTime.now();
        this.point = point;
    }
}
