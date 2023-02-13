package com.psjw.kakaoshareprototype.controller;

import com.psjw.kakaoshareprototype.config.auth.PrincipalDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * packageName : com.psjw.kakaoshareprototype.controller
 * fileName : IndexController
 * author : psjw
 */
@Controller
@Slf4j
public class IndexController {

    @GetMapping("/test/login")
    public @ResponseBody String testLogin(Authentication authentication,
                                          @AuthenticationPrincipal PrincipalDetails userDetails){
        log.info("/test/login ============");
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        log.info("authentication : {}",principalDetails);

        log.info("userDetails : {}",userDetails.getUser());
        return "세션정보확인하기";
    }

    @ResponseBody
    @GetMapping("/test/oauth/login")
    public String testOauthLogin(Authentication authentication, @AuthenticationPrincipal OAuth2User oauth) { //Authentication 을 DI (의존성 주입)
        System.out.println("/test/oauth/login==========================");
//        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
//        System.out.println("oAuth2User.getAttributes() = " + oAuth2User.getAttributes());
        System.out.println("oauth.getAttributes() = " + oauth.getAttributes());
        return "세션 정보 확인하기";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    // 일반로그인, OAuth 로그인 모두 PrincipalDetails로 접근가능
    @GetMapping("/user")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails){
        log.info("principalDetails : {}",principalDetails);
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin(){
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager(){
        return "manager";
    }



    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard/dashboard";
    }

    @GetMapping("/joinProc")
    public @ResponseBody String joinProc(){
        return "회원가입 완료됨";
    }
}
