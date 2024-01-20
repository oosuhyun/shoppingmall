package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.Member;
import com.example.shoppingmall.entity.Product;
import com.example.shoppingmall.entity.Review;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Builder
public class ReviewRes {

    private Long reviewId;
    private String reviewContent;
    private String reviewImg;
    private Long reviewRating;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Member member;
    private Product product;

    public static ReviewRes toDTO(Review review){
        return ReviewRes.builder()
                .reviewId(review.getReviewId())
                .reviewContent(review.getReviewContent())
                .reviewImg(review.getReviewImg())
                .reviewRating(review.getReviewRating())
                .createdDate(review.getCreatedDate())
                .modifiedDate(review.getModifiedDate())
                .member(review.getMember())
                .product(review.getProduct())
                .build();
    }

}
