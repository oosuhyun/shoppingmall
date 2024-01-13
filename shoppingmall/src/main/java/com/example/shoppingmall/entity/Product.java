package com.example.shoppingmall.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName; //상품명
    private String productImg1; //상품 이미지
    private String productImg2;
    private String productImg3;
    private String productImg4;
    private String productImg5;
    private String productDescription; //상품 설명
    private Long productPrice; //상품 가격
    private Long productRestCnt; // 재고
    private String productStatus; // default: "T"
    private String productCategory; //카테고리

}
