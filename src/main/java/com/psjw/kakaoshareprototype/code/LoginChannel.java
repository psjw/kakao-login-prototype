package com.psjw.kakaoshareprototype.code;

/**
 * packageName : com.psjw.kakaoshareprototype.code
 * fileName : LoginChannel
 * author : psjw
 */
public enum LoginChannel {
    GOOGLE("google"), KAKAO("kakao"), FACEBOOK("facebook"), NAVER("naver");
    private String code;

    LoginChannel(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
