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
    @Column(name = "user_id") // 외래키
    private String userId;

    @MapsId("userId")
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "point")
    private Integer point;

    @Builder
    public Point(Integer point, String userId) {
        this.point = point;
        this.userId = userId;
    }
}
