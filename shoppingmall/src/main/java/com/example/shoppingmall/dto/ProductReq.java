package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.Member;
import com.example.shoppingmall.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProductReq {

    private String productName;
    private String productImg1;
    private String productImg2;
    private String productImg3;
    private String productImg4;
    private String productImg5;
    private String productDescription;
    private Long productPrice;
    private Long productRestCnt;
    private String productCategory;
    private Long id; //member 고유키인 id를 입력받음


    public Product toEntity(ProductReq req, Member member) {
        return Product.builder()
                .productName(req.productName)
                .productImg1(req.productImg1)
                .productImg2(req.productImg2)
                .productImg3(req.productImg3)
                .productImg4(req.productImg4)
                .productImg5(req.productImg5)
                .productDescription(req.productDescription)
                .productPrice(req.productPrice)
                .productRestCnt(req.productRestCnt)
                .productStatus("T")
                .productCategory(req.productCategory)
                .member(member)
                .build();
    }
}
