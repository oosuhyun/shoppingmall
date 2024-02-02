package com.example.shoppingmall.repository;


import com.example.shoppingmall.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    //내 장바구니 조회
    List<Cart> findByMember_Id(Long id);

    //장바구니에 이미 있는 상품인지 확인
    Cart findByProduct_ProductIdAndMember_Id(Long productId, Long MemberId);

}
