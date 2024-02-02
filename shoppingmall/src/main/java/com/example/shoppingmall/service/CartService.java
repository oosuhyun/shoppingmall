package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.CartReq;
import com.example.shoppingmall.dto.CartRes;
import com.example.shoppingmall.entity.Cart;
import com.example.shoppingmall.entity.Member;
import com.example.shoppingmall.entity.Product;
import com.example.shoppingmall.repository.CartRepository;
import com.example.shoppingmall.repository.MemberRepository;
import com.example.shoppingmall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    //장바구니 넣기
    public void create(CartReq req){

        Cart cart = cartRepository.findByProduct_ProductIdAndMember_Id(req.getProductId(), req.getMemberId());

        if (cart == null){
            Member member = memberRepository.findById(req.getMemberId())
                    .orElseThrow(EntityExistsException::new);
            Product product = productRepository.findById(req.getProductId())
                    .orElseThrow(EntityExistsException::new);
            cartRepository.save(req.toEntity(req, member, product));
        } else {
            //이미 장바구니에 담겨있는 상품일 시 개수만 추가
            cart.setCnt(req.getCnt() + cart.getCnt());
            cartRepository.save(cart);
        }

    }

    //내 장바구니 조회
    public List<CartRes> findByMember_Id(Long id){
        return cartRepository.findByMember_Id(id)
                .stream()
                .map(CartRes::toDTO)
                .collect(Collectors.toList());
    }

    //장바구니-상품 삭제
    public void deleteById(Long id){
        cartRepository.deleteById(id);
    }

}
