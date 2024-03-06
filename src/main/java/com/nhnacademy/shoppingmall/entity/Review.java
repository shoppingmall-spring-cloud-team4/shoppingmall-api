package com.nhnacademy.shoppingmall.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer reviewId;

    private Integer rating; // 별점

    @Column(name = "review_date_created")
    private LocalDateTime reviewDateCreated; // 리뷰생성일

    @Column(name = "comments")
    @Length(max = 300)
    private String comment; // 리뷰내용

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 구매자 아이디

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product; // 구매 상품번호

    @Builder
    public Review(Integer rating, LocalDateTime reviewDateCreated, String comment, User user, Product product) {
        this.rating = rating;
        this.reviewDateCreated = reviewDateCreated;
        this.comment = comment;
        this.user = user;
        this.product = product;
    }
}
