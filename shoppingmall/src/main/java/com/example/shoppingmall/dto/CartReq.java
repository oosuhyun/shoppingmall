package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.Cart;
import com.example.shoppingmall.entity.Member;
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
public class CartReq {

    private Long cnt;
    private Long memberId;
    private Long productId;

    public Cart toEntity(CartReq req, Member member, Product product){
        return Cart.builder()
                .cnt(req.cnt)
                .member(member)
                .product(product)
                .build();
    }

}
