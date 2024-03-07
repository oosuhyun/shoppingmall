package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.CartReq;
import com.example.shoppingmall.dto.CartRes;
import com.example.shoppingmall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    //장바구니 넣기
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CartReq req){
        cartService.create(req);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    //내 장바구니 조회
    @GetMapping("/{id}")
    public ResponseEntity<List<CartRes>> getByMember_Id(@PathVariable Long id){
        return ResponseEntity
                .ok(cartService.findByMember_Id(id));
    }

    //주문할 장바구니 상품 조회
    @GetMapping("/some")
    public ResponseEntity<List<CartRes>> getSome(@RequestParam List<Long> ids){
        return ResponseEntity
                .ok(cartService.findSome(ids));
    }

    //장바구니-상품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        cartService.deleteById(id);
        return ResponseEntity
                .ok("장바구니에서 상품 삭제 완료");
    }

}
