package com.example.shoppingmall.dto;

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
    private String productImg1;
    private String productImg2;
    private String productImg3;
    private String productImg4;
    private String productImg5;
    private String productDescription;
    private Long productPrice;
    private Long productRestCnt;
    private String productStatus;
    private String productCategory;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public static ProductRes toDTO(Product product){
        return ProductRes.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productImg1(product.getProductImg1())
                .productImg2(product.getProductImg2())
                .productImg3(product.getProductImg3())
                .productImg4(product.getProductImg4())
                .productImg5(product.getProductImg5())
                .productDescription(product.getProductDescription())
                .productPrice(product.getProductPrice())
                .productRestCnt(product.getProductRestCnt())
                .productStatus(product.getProductStatus())
                .productCategory(product.getProductCategory())
                .createdDate(product.getCreatedDate())
                .modifiedDate(product.getModifiedDate())
                .build();
    }
}
