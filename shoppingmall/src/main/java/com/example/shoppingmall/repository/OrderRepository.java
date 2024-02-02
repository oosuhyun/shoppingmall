package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {

    //내 주문 목록
    Page<Order> findByMemberId(Long id, Pageable pageable);

}
