package com.example.shoppingmall.repository;

import com.example.shoppingmall.dto.ProductRes;
import com.example.shoppingmall.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //특정 카테고리 조회-페이지네이션
    //판매 중인 것만 표기
    Page<Product> findByProductCategoryAndProductStatus(String category, String status, Pageable pageable);

}
