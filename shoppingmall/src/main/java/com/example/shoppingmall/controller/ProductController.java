package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.ProductReq;
import com.example.shoppingmall.dto.ProductRes;
import com.example.shoppingmall.service.ProductService;
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
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    //상품 생성
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ProductReq req){
        productService.create(req);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    //상품 단일 조회
    @GetMapping("/{id}")
    public ResponseEntity<ProductRes> getById(@PathVariable Long id){
        return ResponseEntity
                .ok(productService.findById(id));
    }

    //특정 카테고리 조회-페이지네이션
    //판매 중인 것만 표기
    @GetMapping
    public ResponseEntity<Page<ProductRes>> getByProductCategoryAndProductStatus(
            @RequestParam("category") String category,
            @PageableDefault(size = 10, sort = {"createdDate"}, direction = Sort.Direction.DESC)
            Pageable pageable
    ){
        return ResponseEntity
                .ok(productService.findByProductCategoryAndProductStatus(category, "T", pageable));
    }

    //판매 중지 or 재고 없음
    @DeleteMapping("/{id}")
    public ResponseEntity<String> stop(@PathVariable Long id){
        productService.stop(id);
        return new ResponseEntity<>("판매 중지로 상태 변경", HttpStatus.OK);
    }

}
