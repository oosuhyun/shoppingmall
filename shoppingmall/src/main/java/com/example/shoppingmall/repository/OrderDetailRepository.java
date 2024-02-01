package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    //주문번호로 주문상품 조회
    List<OrderDetail> findByOrderId(String orderId);

}
