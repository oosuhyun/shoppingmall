package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.Member;
import com.example.shoppingmall.entity.Product;
import com.example.shoppingmall.entity.Review;
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
public class ReviewReq {

    private String reviewContent;
    private String reviewImg;
    private Long reviewRating;
    private Long memberId;
    private Long productId;

    public Review toEntity(ReviewReq req, Member member, Product product){
        return Review.builder()
                .reviewContent(req.reviewContent)
                .reviewImg(req.reviewImg)
                .reviewRating(req.reviewRating)
                .member(member)
                .product(product)
                .build();
    }

}
