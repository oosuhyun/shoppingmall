package com.example.shoppingmall.controller;

import com.example.shoppingmall.dto.MemberRes;
import com.example.shoppingmall.dto.MemberUpdateReq;
import com.example.shoppingmall.jwt.TokenDto;
import com.example.shoppingmall.dto.MemberJoinReq;
import com.example.shoppingmall.dto.MemberLoginReq;
import com.example.shoppingmall.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    //회원가입
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody MemberJoinReq req){
        if(memberService.checkMemberIdDuplication(req.getMemberId())){
            return new ResponseEntity<>("아이디가 중복됩니다", HttpStatus.CONFLICT);
        }
        memberService.join(req);
        return new ResponseEntity<>("회원가입 성공", HttpStatus.OK);

    }

    //로그인
    @PostMapping("/login")
    public TokenDto login(@RequestBody MemberLoginReq memberLoginReq) {
        String memberId = memberLoginReq.getMemberId();
        String password = memberLoginReq.getPassword();
        TokenDto tokenDto = memberService.login(memberId, password);
        return tokenDto;
    }

    //로그인 사용자 정보 확인
    @GetMapping("/info")
    public ResponseEntity<MemberRes> getByMemberId(Authentication auth){
        return ResponseEntity
                .ok(memberService.findByMemberId(auth.getName()));
    }

    @PostMapping("/test")
    public String test() {
        return "sucess";
    }

    //내 정보 수정
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody MemberUpdateReq req){
        memberService.update(req);
        return new ResponseEntity<>("내 정보 업데이트 성공", HttpStatus.OK);

    }

}
