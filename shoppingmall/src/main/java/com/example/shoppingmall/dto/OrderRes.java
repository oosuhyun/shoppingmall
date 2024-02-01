package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.Order;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Builder
public class OrderRes {

    private String orderId;
    private Long orderTotalPrice;
    private String orderStatus;
    private String memberName;
    private String memberTel;
    private String memberGender;
    private String memberZipCode;
    private String memberAddress;
    private String memberAddressDetail;
    private String memberRequirements;
    private Long memberId;
    private LocalDateTime createdDate;

    public static OrderRes toDTO(Order order){
        return OrderRes.builder()
                .orderId(order.getOrderId())
                .orderTotalPrice(order.getOrderTotalPrice())
                .orderStatus(order.getOrderStatus())
                .memberName(order.getMemberName())
                .memberTel(order.getMemberTel())
                .memberGender(order.getMemberGender())
                .memberZipCode(order.getMemberZipCode())
                .memberAddress(order.getMemberAddress())
                .memberAddressDetail(order.getMemberAddressDetail())
                .memberRequirements(order.getMemberRequirements())
                .memberId(order.getMemberId())
                .createdDate(order.getCreatedDate())
                .build();
    }

}
