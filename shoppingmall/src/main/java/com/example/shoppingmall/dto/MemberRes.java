package com.example.shoppingmall.dto;

import com.example.shoppingmall.entity.Member;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Builder
public class MemberRes {

    private Long id;
    private String memberId;
    private String password;
    private String memberName;
    private String memberTel;
    private String memberGender;
    private String memberStatus;
    private String memberZipCode;
    private String memberAddress;
    private String memberAddressDetail;
    private String memberRequirements;

    public static MemberRes toDTO(Member member){
        return MemberRes.builder()
                .id(member.getId())
                .memberId(member.getMemberId())
                .password(member.getPassword())
                .memberName(member.getMemberName())
                .memberTel(member.getMemberTel())
                .memberGender(member.getMemberGender())
                .memberStatus(member.getMemberStatus())
                .memberZipCode(member.getMemberZipCode())
                .memberAddress(member.getMemberAddress())
                .memberAddressDetail(member.getMemberAddressDetail())
                .memberRequirements(member.getMemberRequirements())
                .build();
    }


}
