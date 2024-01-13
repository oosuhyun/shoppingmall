package com.example.shoppingmall.security;

import com.example.shoppingmall.entity.Member;
import com.example.shoppingmall.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 회원 아이디라는 용어 대신 username이라는 단어를 사용합니다. 스프링 시큐리티에서는
 * username이라는 단어 자체가 회원을 구별할 수 있는 식별 데이터를 의미합니다. 문자열로
 * 처리하는 점은 같지만 일반적으로 사용하는 회원의 이름이 아니라 오히려 id에 해당합니다.
 *
 * username과 password를 동시에 사용하지 않습니다. 스프링 시큐리티는 UserDetailsService를
 * 이용해서 회원의 존재만을 우선적으로 가져오고, 이후에 password가 틀리면 'Bad Cridential(잘못된 자격증명)'이라는
 * 결과를 만들어 냅니다.(인증)
 *
 * 사용자의 username과 password로 인증 과정이 끝나면 원하는 자원(URL)에 접근할 수 있는
 * 적절한 권한이 있는지를 확인하고 인가 과정을 실행합니다.
 * 이 과정에서는 'Access Denied'와 같은 결과가 만들어 집니다.
 */

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByMemberId(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
    /**
     * 여기서 PasswordEncoder를 통해 UserDetails 객체를 생성할 때 encoding을 해줌 => 왜냐하면 Spring Security는 사용자 검증을 위해 encoding된 password와 그렇지 않은 password를 비교하기 때문
     * 실제로는 DB 자체에 encoding된 password 값을 갖고 있고 그냥 memer.getPassword()로 encoding된 password를 꺼내는 것이 좋지만, 예제에서는 편의를 위해 검증 객체를 생성할 때 encoding을 해줌.
     */
    private UserDetails createUserDetails(Member member) {
        return User.builder()
                .username(member.getUsername())
                .password(member.getPassword())
                .roles(member.getRoles().toArray(new String[0]))
                .build();
    }
}
