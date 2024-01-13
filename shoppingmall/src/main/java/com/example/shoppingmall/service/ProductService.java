package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.ProductReq;
import com.example.shoppingmall.dto.ProductRes;
import com.example.shoppingmall.entity.Member;
import com.example.shoppingmall.entity.Product;
import com.example.shoppingmall.repository.MemberRepository;
import com.example.shoppingmall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    // 상품 생성
    public void create(ProductReq req){
        Member member = memberRepository.findById(req.getId())
                        .orElseThrow(EntityExistsException::new);
        productRepository.save(req.toEntity(req, member));
    }

    //상품 단일 조회
    public ProductRes findById(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);  //null 일 경우 예외처리
        return ProductRes.toDTO(product);
    }

    //특정 카테고리 조회-페이지네이션
    //판매 중인 것만 표기
    public Page<ProductRes> findByProductCategoryAndProductStatus(String category, String status, Pageable pageable){
        return productRepository.findByProductCategoryAndProductStatus(category, status, pageable)
                .map(ProductRes::toDTO);
    }

    //판매 중지 or 재고 없음
    public void stop(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        product.setProductStatus("F");

        productRepository.save(product);
    }

}
