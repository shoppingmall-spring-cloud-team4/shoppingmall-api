package com.nhnacademy.shoppingmall.entity;

import lombok.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    Integer categoryId;
  
    @Column(name = "category_name")
    @Length(min = 1, max = 20)
    String categoryName; //카테고리 이름

    @Builder
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

}
