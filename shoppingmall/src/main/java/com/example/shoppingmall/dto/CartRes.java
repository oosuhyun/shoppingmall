package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.Cart;
import com.example.shoppingmall.entity.Member;
import com.example.shoppingmall.entity.Product;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Builder
public class CartRes {

    private Long cartId;
    private Long cnt;
    private Member member;
    private Product product;

    public static CartRes toDTO(Cart cart){
        return CartRes.builder()
                .cartId(cart.getCartId())
                .cnt(cart.getCnt())
                .member(cart.getMember())
                .product(cart.getProduct())
                .build();
    }

}


