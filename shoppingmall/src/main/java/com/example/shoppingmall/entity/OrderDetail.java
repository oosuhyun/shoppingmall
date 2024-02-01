package com.example.shoppingmall.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailId;
    private Long orderDetailPrice;
    private Long orderDetailCnt;
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

}
