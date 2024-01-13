package com.example.shoppingmall.service;

import com.example.shoppingmall.dto.MemberJoinReq;
import com.example.shoppingmall.dto.MemberRes;
import com.example.shoppingmall.entity.Member;
import com.example.shoppingmall.jwt.JwtTokenProvider;
import com.example.shoppingmall.jwt.TokenDto;

import com.example.shoppingmall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    public void join(MemberJoinReq req){
        String encodedPassword = passwordEncoder.encode(req.getPassword());
        req.setPassword(encodedPassword);
        memberRepository.save(req.toEntity());
    }

    //회원가입 시 로그인아이디 중복 확인
    public boolean checkMemberIdDuplication(String memberId){
        return memberRepository.existsByMemberId(memberId);
    }

    /**
     * 1. 로그인 요청으로 들어온 ID, PWD 기반으로 Authentication 객체 생성
     * 2. authenticate() 메서드를 통해 요청된 Member에 대한 검증이 진행 => loadUserByUsername 메서드를 실행. 해당 메서드는 검증을 위한 유저 객체를 가져오는 부분으로써, 어떤 객체를 검증할 것인지에 대해 직접 구현
     * 3. 검증이 정상적으로 통과되었다면 인증된 Authentication객체를 기반으로 JWT 토큰을 생성
     */
    @Transactional
    public TokenDto login(String memberId, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberId, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = jwtTokenProvider.generateToken(authentication);

        return tokenDto;
    }

    //로그인 사용자 정보 확인
    public MemberRes findByMemberId(String memberId){
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(EntityExistsException::new);

        return MemberRes.toDTO(member);
    }

}