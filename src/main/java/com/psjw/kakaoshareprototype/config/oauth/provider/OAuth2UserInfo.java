package com.psjw.kakaoshareprototype.config.oauth.provider;

/**
 * packageName : com.psjw.kakaoshareprototype.config.oauth.provider
 * fileName : OAuth2UserInfo
 * author : psjw
 */
public interface OAuth2UserInfo {
    String getProviderId();

    public String getProvider();

    String getEmail();

    String getName();
}
