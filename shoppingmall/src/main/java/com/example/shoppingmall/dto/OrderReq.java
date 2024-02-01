package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class OrderReq {

    private String orderId;
    private Long orderTotalPrice;
    private String memberName;
    private String memberTel;
    private String memberGender;
    private String memberZipCode;
    private String memberAddress;
    private String memberAddressDetail;
    private String memberRequirements;
    private Long memberId;

    public Order toEntity(){
        return Order.builder()
                .orderId(this.orderId)
                .orderTotalPrice(this.orderTotalPrice)
                .orderStatus("Preparing")
                .memberName(this.memberName)
                .memberTel(this.memberTel)
                .memberGender(this.memberGender)
                .memberZipCode(this.memberZipCode)
                .memberAddress(this.memberAddress)
                .memberAddressDetail(this.memberAddressDetail)
                .memberRequirements(this.memberRequirements)
                .memberId(this.memberId)
                .build();
    }

}
