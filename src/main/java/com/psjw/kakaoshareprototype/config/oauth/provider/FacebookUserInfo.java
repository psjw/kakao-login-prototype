package com.psjw.kakaoshareprototype.config.oauth.provider;

import com.psjw.kakaoshareprototype.code.LoginChannel;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * packageName : com.psjw.kakaoshareprototype.config.oauth.provider
 * fileName : GoolgeUserInfo
 * author : psjw
 */
@RequiredArgsConstructor
public class FacebookUserInfo implements OAuth2UserInfo{

    private final Map<String, Object> attributes; //

    @Override
    public String getProviderId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getProvider() {
        return LoginChannel.FACEBOOK.getCode();
    }

    @Override
    public String getEmail() {
        return String.valueOf(attributes.get("email"));
    }

    @Override
    public String getName() {
        return String.valueOf(attributes.get("name"));
    }
}
