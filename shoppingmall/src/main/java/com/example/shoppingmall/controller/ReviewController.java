package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.ReviewReq;
import com.example.shoppingmall.dto.ReviewRes;
import com.example.shoppingmall.service.ReviewService;
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
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    //후기 생성
    @PostMapping
    public ResponseEntity<Void> create(
            @RequestPart(value = "req") ReviewReq req,
            @RequestPart(value = "file") MultipartFile multipartFile
    ){
        String imgURL = reviewService.uploadFile(multipartFile);
        reviewService.create(req, imgURL);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    //내가 작성한 후기 조회
    @GetMapping("/my")
    public ResponseEntity<Page<ReviewRes>> getByMember_Id(
            @RequestParam("id") Long id,
            @PageableDefault(size = 10, sort = {"createdDate"}, direction = Sort.Direction.DESC)
            Pageable pageable
    ){
        return ResponseEntity
                .ok(reviewService.findByMember_Id(id, pageable));
    }

    //상품 후기 조회
    @GetMapping("/product")
    public ResponseEntity<Page<ReviewRes>> getByProduct_ProductId(
            @RequestParam("id") Long id,
            @PageableDefault(size = 10, sort = {"createdDate"}, direction = Sort.Direction.DESC)
            Pageable pageable
    ){
        return ResponseEntity
                .ok(reviewService.findByProduct_ProductId(id, pageable));
    }

    //후기 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        reviewService.deleteById(id);
        return ResponseEntity
                .ok("후기 삭제 완료");
    }

}
