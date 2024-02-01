package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.OrderReq;
import com.example.shoppingmall.dto.OrderRes;
import com.example.shoppingmall.entity.OrderDetail;
import com.example.shoppingmall.repository.OrderDetailRepository;
import com.example.shoppingmall.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    //상품 주문
    public void order(OrderReq req){
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(req.getOrderId());
        Long total = Long.valueOf(0);
        for (OrderDetail orderDetail : orderDetails){
            total += orderDetail.getOrderDetailPrice() * orderDetail.getOrderDetailCnt();
        }
        req.setOrderTotalPrice(total);
        orderRepository.save(req.toEntity());
    }

    //내 주문 목록
    public Page<OrderRes> findByMemberId(Long id, Pageable pageable){
        return orderRepository.findByMemberId(id, pageable)
                .map(OrderRes::toDTO);
    }

}
