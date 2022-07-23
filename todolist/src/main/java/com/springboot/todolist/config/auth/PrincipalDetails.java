package com.springboot.todolist.config.auth;

//시큐리티가 /login 요청을 낚아채서 로그인을 진행 시킨다.
// 로그인 진행이 완료가되면 session 을 만들어줍니다.
// 이때 시큐리티가 자신만의 security session 이 있다.
// (Security Context Holder 라는 키값에 세션을 저장)
// 오브젝트 타입 => Authentication 타입의 객체여야 session 에 들어 갈 수 있다.
//Authentication 안에 User 정보가 있어야됨.
// User 오브젝트의 타입은 UserDeatils 타입 객체여야함.

// Security Session 에 저장하는데 이때 Authentication 객체여야함
// Authentication 객체 내부의 UserDetails 객체여야함

import com.springboot.todolist.domain.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

    private User user; // 콤포지션
    private Map<String, Object> attributes;

    public PrincipalDetails(User user) { // 일반 로그인
        this.user = user;
    }

    public PrincipalDetails(User user, Map<String, Object> attributes) { // oauth 로그인
        this.user = user;
        this.attributes = attributes;
    }
    @Override
    public Map<String, Object> getAttributes() {

        return attributes;
    }

    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }

    // 해당 User의 권한을 리턴하는 메소드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });

        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        //회원이 1년동안 로그인을 안한다면 휴먼계정으로 전환한다고 가정,
        // 이 때 사용 : 마지막 로그인 시간 - 지금 > 1년 => false
       return true;
    }

    @Override
    public String getName() {
        return null;
    }
}
