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
    private String productImg;
    private String productDescription;
    private Long productPrice;
    private Long productRestCnt;
    private String productCategory;
    private Long id; //member 고유키인 id를 입력받음


    public Product toEntity(ProductReq req, Member member) {
        return Product.builder()
                .productName(req.productName)
                .productImg(req.productImg)
                .productDescription(req.productDescription)
                .productPrice(req.productPrice)
                .productRestCnt(req.productRestCnt)
                .productStatus("T")
                .productCategory(req.productCategory)
                .member(member)
                .build();
    }
}
