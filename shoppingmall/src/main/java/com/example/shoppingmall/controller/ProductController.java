package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.ProductReq;
import com.example.shoppingmall.dto.ProductRes;
import com.example.shoppingmall.service.PhotoService;
import com.example.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final PhotoService photoService;

    //상품 생성
    @PostMapping
    public ResponseEntity<Void> create(
            @RequestPart(value = "req") ProductReq req,
            @RequestPart(value = "file") MultipartFile multipartFile
    ){
        String imgURL = photoService.uploadFile(multipartFile);
        productService.create(req, imgURL);
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
    @GetMapping("/filter")
    public ResponseEntity<Page<ProductRes>> getByProductCategoryAndProductStatus(
            @RequestParam("category") String category,
            @PageableDefault(size = 9, sort = {"createdDate"}, direction = Sort.Direction.DESC)
            Pageable pageable
    ){
        return ResponseEntity
                .ok(productService.findByProductCategoryAndProductStatus(category, "T", pageable));
    }

    //상품 이름 키워드 검색
    @GetMapping("/search")
    public ResponseEntity<Page<ProductRes>> getByProductNameContaining(
            @RequestParam("keyword") String keyword,
            @PageableDefault(size = 9, sort = {"createdDate"}, direction = Sort.Direction.DESC)
            Pageable pageable
    ){
        return ResponseEntity
                .ok(productService.findByProductNameContaining(keyword, pageable));
    }

    //판매 중지 or 재고 없음
    @DeleteMapping("/{id}")
    public ResponseEntity<String> stop(@PathVariable Long id){
        productService.stop(id);
        return new ResponseEntity<>("판매 중지로 상태 변경", HttpStatus.OK);
    }

}
