package com.psjw.kakaoshareprototype.config.auth;

import com.psjw.kakaoshareprototype.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * packageName : com.psjw.kakaoshareprototype.config.auth
 * fileName : PrincipalDetails
 * author : psjw
 */
//시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행
//로그인진행이 완료가 되면 시큐리티 session을 만들어 줌(SecurityContextHolder)
//오브젝트 타입 => Authentication 타입 객체
//Authentication안에 User정보 있어야됨
//User오브젝트 타입 => UserDetails 타입 객체
// Security Session => Authentication 객체 => UserDetails(PricipaDetails)
@RequiredArgsConstructor //일반로그인
@AllArgsConstructor //OAuth로그인
@Data
public class PrincipalDetails implements UserDetails, OAuth2User {
    private final User user;//콤포지션
    private Map<String, Object> attributes;

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    //해당 User의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(user::getRole);
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
        return  true;
    }

    @Override
    public boolean isEnabled() {
        // 1년동안 로그인 안한경우 휴면계정
        //현재시간 - 로긴시간 => 1년 초과하면 return false
        return true;
    }

    @Override
    public String getName() {
        return String.valueOf(attributes.get("sub"));
    }
}
