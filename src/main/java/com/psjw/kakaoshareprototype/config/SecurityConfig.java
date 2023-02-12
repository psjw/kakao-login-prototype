package com.psjw.kakaoshareprototype.config;

import com.psjw.kakaoshareprototype.config.auth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * packageName : com.psjw.kakaoshareprototype.config
 * fileName : SecurityConfig
 * author : psjw
 */
@Configuration
@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인이 등록
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) //secured 어노테이션 활성화, preAuthorize, PostAuthorize 어노테이션 활성화
@RequiredArgsConstructor
public class SecurityConfig  {

    private final PrincipalOauth2UserService principalOauth2UserService;
//구글로그인 완료 이후 후처리
// 1. 코드받기
// 2. 엑세스토큰(권한)
// 3. 사용자프로필정보를 가져오고
// 4-1. 그정보를 토대로 회원가입을 자동으로 진행
// 4-2. (이메일, 전화번호, 이름, 아이디) 쇼핑몰 -> 추가정보 필요 (집주소, 회원등급..등등)

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        return  http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .usernameParameter("email")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/dashboard")
                .and()
                .oauth2Login()
                .loginPage("/loginForm") //구글로그인 완료 후 Tip. 코드X (엑세스토큰+사용자프로필정보 O)
                .userInfoEndpoint()
                .userService(principalOauth2UserService)
                .and()
                .and()
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**","/img/**","/scss/**","/vendor/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
