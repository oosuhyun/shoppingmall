package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.OrderDetailReq;
import com.example.shoppingmall.dto.OrderDetailRes;
import com.example.shoppingmall.entity.Product;
import com.example.shoppingmall.service.OrderDetailService;
import com.example.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orderDetail")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    //단일 상품 주문 등록
    @PostMapping
    public ResponseEntity<String> create(@RequestBody OrderDetailReq req){
        String str = orderDetailService.createRandomNumber();
        orderDetailService.create(req, str);
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    //카트 상품 장바구니 -> 주문상품으로 등록
    @PostMapping("/some")
    public ResponseEntity<String> createSome(@RequestParam List<Long> ids){
        String str = orderDetailService.createRandomNumber();
        orderDetailService.createSome(ids, str);
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    //주문 상품 확인
    @GetMapping
    public ResponseEntity<List<OrderDetailRes>> getByOrderId(
            @RequestParam("id") String id
    ){
        return ResponseEntity
                .ok(orderDetailService.findByOrderId(id));
    }

}
