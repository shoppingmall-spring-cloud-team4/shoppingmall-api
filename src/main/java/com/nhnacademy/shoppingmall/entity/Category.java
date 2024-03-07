package com.nhnacademy.shoppingmall.entity;

import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "category_name")
    @Length(min = 1, max = 20)
<<<<<<< HEAD
    private String categoryName; //카테고리 이름
=======
    String categoryName; // 카테고리 이름
>>>>>>> fbc4974287ff06e5a72b4bba526dd06e8be4e9d9

    @Builder
    public Category(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

}
