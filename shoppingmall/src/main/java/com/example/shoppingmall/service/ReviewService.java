package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.ReviewReq;
import com.example.shoppingmall.dto.ReviewRes;
import com.example.shoppingmall.entity.Member;
import com.example.shoppingmall.entity.Product;
import com.example.shoppingmall.repository.MemberRepository;
import com.example.shoppingmall.repository.ProductRepository;
import com.example.shoppingmall.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    //후기 생성
    public void create(ReviewReq req){
        Member member = memberRepository.findById(req.getMemberId())
                .orElseThrow(EntityExistsException::new);
        Product product = productRepository.findById(req.getProductId())
                .orElseThrow(EntityExistsException::new);
        reviewRepository.save(req.toEntity(req, member, product));
    }

    //내가 작성한 후기 조회
    public Page<ReviewRes> findByMember_Id(Long id, Pageable pageable){
        return reviewRepository.findByMember_Id(id, pageable)
                .map(ReviewRes::toDTO);
    }

    //상품 후기 조회
    public Page<ReviewRes> findByProduct_ProductId(Long id, Pageable pageable){
        return reviewRepository.findByProduct_ProductId(id, pageable)
                .map(ReviewRes::toDTO);
    }

    //후기 삭제
    public void deleteById(Long id){
        reviewRepository.deleteById(id);
    }

}
