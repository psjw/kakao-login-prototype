package com.psjw.kakaoshareprototype.config.oauth;

import com.psjw.kakaoshareprototype.code.LoginChannel;
import com.psjw.kakaoshareprototype.config.auth.PrincipalDetails;
import com.psjw.kakaoshareprototype.config.oauth.provider.FacebookUserInfo;
import com.psjw.kakaoshareprototype.config.oauth.provider.GoogleUserInfo;
import com.psjw.kakaoshareprototype.config.oauth.provider.OAuth2UserInfo;
import com.psjw.kakaoshareprototype.dto.UserRequestDto;
import com.psjw.kakaoshareprototype.mapper.UserMapper;
import com.psjw.kakaoshareprototype.model.User;
import com.psjw.kakaoshareprototype.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * packageName : com.psjw.kakaoshareprototype.config.auth
 * fileName : PricipalOauth2UserService
 * author : psjw
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    //구글로 부타 받은 userRequest 데이터에 대한 후처리되는 함수
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //registrationId로 어떤 OAuth로 로그인했는지 알수 있음
        log.info("userRequest.getClientRegistration() : {}",userRequest.getClientRegistration());
        log.info("userRequest.getAccessToken() : {}",userRequest.getAccessToken().getTokenValue());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        // userRequest 정보 :구글 로기인 버튼 클릭 -> 구글로그인 창 -> 로그인을 완료 -> code를 리턴(OAuth-Client 라이브러리) -> AccessToken 요청
        // userRequest 정보 -> loadUser함수 호출 -> 구글로부터 회원 프로필 받아준다
        log.info("super.loadUser(userRequest) : {}",oAuth2User);
        OAuth2UserInfo oAuth2UserInfo = null;
        if(userRequest.getClientRegistration().getRegistrationId().equals(LoginChannel.GOOGLE.getCode())){
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }
        if(userRequest.getClientRegistration().getRegistrationId().equals(LoginChannel.FACEBOOK.getCode())){
            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        }

        User userEntity = userRepository.findByEmailAndProvider(oAuth2UserInfo.getEmail(), oAuth2UserInfo.getProvider());

        if(Objects.isNull(userEntity)){
            UserRequestDto userRequestDto = UserRequestDto.builder()
                    .provider(oAuth2UserInfo.getProvider())
                    .providerId(oAuth2UserInfo.getProviderId())
                    .username(oAuth2UserInfo.getName())
                    .password("psjw")
                    .email(oAuth2UserInfo.getEmail())
                    .role("ROLE_USER")
                    .build();
            userEntity = UserMapper.INSTNACE.toEntity(userRequestDto);
            userRepository.save(userEntity);
        }


        return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }
}
