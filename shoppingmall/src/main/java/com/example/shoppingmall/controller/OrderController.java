package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.OrderReq;
import com.example.shoppingmall.dto.OrderRes;
import com.example.shoppingmall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    //상품 주문
    @PostMapping
    public ResponseEntity<Void> orderOne(@RequestBody OrderReq req){
        orderService.order(req);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    //내 주문 목록
    @GetMapping("/my")
    public ResponseEntity<Page<OrderRes>> getByMemberId(
            @RequestParam("id") Long id,
            @PageableDefault(sort = {"createdDate"}, direction = Sort.Direction.DESC)
            Pageable pageable
    ){
        return ResponseEntity
                .ok(orderService.findByMemberId(id, pageable));
    }

}
