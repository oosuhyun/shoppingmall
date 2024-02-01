package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.Member;
import com.example.shoppingmall.entity.Product;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Builder
public class ProductRes {

    private Long productId;
    private String productName;
    private String productImg;
    private String productDescription;
    private Long productPrice;
    private Long productRestCnt;
    private String productStatus;
    private String productCategory;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Member member;

    public static ProductRes toDTO(Product product){
        return ProductRes.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productImg(product.getProductImg())
                .productDescription(product.getProductDescription())
                .productPrice(product.getProductPrice())
                .productRestCnt(product.getProductRestCnt())
                .productStatus(product.getProductStatus())
                .productCategory(product.getProductCategory())
                .createdDate(product.getCreatedDate())
                .modifiedDate(product.getModifiedDate())
                .member(product.getMember())
                .build();
    }
}
