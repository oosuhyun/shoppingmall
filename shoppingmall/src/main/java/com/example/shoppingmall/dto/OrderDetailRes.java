package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.OrderDetail;
import com.example.shoppingmall.entity.Product;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Builder
public class OrderDetailRes {

    private Long orderDetailId;
    private Long orderDetailPrice;
    private Long orderDetailCnt;
    private String orderId;
    private Product product;

    public static OrderDetailRes toDTO(OrderDetail orderDetail){
        return OrderDetailRes.builder()
                .orderDetailId(orderDetail.getOrderDetailId())
                .orderDetailPrice(orderDetail.getOrderDetailPrice())
                .orderDetailCnt(orderDetail.getOrderDetailCnt())
                .orderId(orderDetail.getOrderId())
                .product(orderDetail.getProduct())
                .build();
    }

}
