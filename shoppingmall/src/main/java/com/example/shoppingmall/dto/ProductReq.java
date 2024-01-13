package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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


    public Product toEntity() {
        return Product.builder()
                .productName(this.productName)
                .productImg1(this.productImg1)
                .productImg2(this.productImg2)
                .productImg3(this.productImg3)
                .productImg4(this.productImg4)
                .productImg5(this.productImg5)
                .productDescription(this.productDescription)
                .productPrice(this.productPrice)
                .productRestCnt(this.productRestCnt)
                .productStatus("T")
                .productCategory(this.productCategory)
                .build();
    }
}
