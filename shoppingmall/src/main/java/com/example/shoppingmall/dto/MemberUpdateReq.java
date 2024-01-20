package com.example.shoppingmall.dto;

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
public class MemberUpdateReq {

    private Long id;
    private String memberId;
    private String memberName;
    private String memberTel;
    private String memberGender;
    private String memberZipCode;
    private String memberAddress;
    private String memberAddressDetail;
    private String memberRequirements;
}
