package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class MemberJoinReq {

    private String memberId;
    private String password;
    private String memberName;
    private String memberTel;
    private String memberGender;
    private String memberZipCode;
    private String memberAddress;
    private String memberAddressDetail;
    private String memberRequirements;

    public Member toEntity(){
        return Member.builder()
                .memberId(this.memberId)
                .password(this.password)
                .memberName(this.memberName)
                .memberTel(this.memberTel)
                .memberGender(this.memberGender)
                .memberStatus("T")
                .memberZipCode(this.memberZipCode)
                .memberAddress(this.memberAddress)
                .memberAddressDetail(this.memberAddressDetail)
                .memberRequirements(this.memberRequirements)
                .roles(Collections.singletonList("USER"))
                .build();
    }

}
