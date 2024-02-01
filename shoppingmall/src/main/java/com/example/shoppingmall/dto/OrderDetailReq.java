package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.Cart;
import com.example.shoppingmall.entity.OrderDetail;
import com.example.shoppingmall.entity.Product;
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
public class OrderDetailReq {

    private Long orderDetailPrice;
    private Long orderDetailCnt;
    private String orderId;
    private Long productId;

    public OrderDetail toEntity(OrderDetailReq req, Product product){
        return OrderDetail.builder()
                .orderDetailPrice(req.orderDetailPrice)
                .orderDetailCnt(req.orderDetailCnt)
                .orderId(req.orderId)
                .product(product)
                .build();
    }

    public static OrderDetail cartToEntity(Cart cart){
        return  OrderDetail.builder()
                .orderDetailPrice(cart.getProduct().getProductPrice())
                .orderDetailCnt(cart.getCnt())
                .product(cart.getProduct())
                .build();
    }
}
