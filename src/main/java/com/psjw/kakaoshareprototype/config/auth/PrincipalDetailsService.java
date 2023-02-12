package com.psjw.kakaoshareprototype.config.auth;

import com.psjw.kakaoshareprototype.model.User;
import com.psjw.kakaoshareprototype.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * packageName : com.psjw.kakaoshareprototype.config.auth
 * fileName : PrincipalDetailsService
 * author : psjw
 */
//시큐리티 설정에서 loginProcessingUrl("/login")
///login 요청이 오면 UserDetailsSeervice 타입으로 IoC 되어 있는 loadUserByUsername 함수가 실행
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    //시큐리티 session( 내부 Authentication( 내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User userEntity = userRepository.findByEmail(email);
        if(Objects.nonNull(userEntity)){
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
