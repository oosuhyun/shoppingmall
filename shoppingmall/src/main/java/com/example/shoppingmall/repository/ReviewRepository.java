package com.example.shoppingmall.repository;

import com.example.shoppingmall.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    //내가 작성한 후기 조회
    Page<Review> findByMember_Id(Long id, Pageable pageable);

    //상품 후기 조회
    Page<Review> findByProduct_ProductId(Long id, Pageable pageable);

}
