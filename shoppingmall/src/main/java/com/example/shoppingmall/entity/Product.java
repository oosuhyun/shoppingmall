package com.example.shoppingmall.entity;

import lombok.*;

import javax.persistence.*;


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

    @ManyToOne
    @JoinColumn(name = "id")
    private Member member; //product와 member의 N:1 연관관계 생성

}
