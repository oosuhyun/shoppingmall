package com.example.shoppingmall.dto;

import lombok.Data;

@Data
public class MemberLoginReq {
    private String memberId;
    private String password;
}