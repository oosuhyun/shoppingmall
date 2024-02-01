package com.example.shoppingmall.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseTime{

    @Id
    private String orderId; //주문번호
    private Long orderTotalPrice; //총 주문 가격
    private String orderStatus; //주문상태
    private String memberName; //이름
    private String memberTel; //전화번호
    private String memberGender; //성별
    private String memberZipCode; //우편주소
    private String memberAddress; //주소
    private String memberAddressDetail; //상세주소
    private String memberRequirements; //요청사항
    private Long memberId;

}
