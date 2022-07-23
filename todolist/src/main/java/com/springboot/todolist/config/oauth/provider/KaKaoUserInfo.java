package com.springboot.todolist.config.oauth.provider;

import java.util.Map;

public class KaKaoUserInfo implements OAuth2UserInfo{
    private Long id;
    private Map<String, Object> attributes;

    public KaKaoUserInfo(Long id, Map<String, Object> attributes) {
        this.id = id;
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return Long.toString((Long)id);
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) ((Map<String, Object>)attributes.get("profile")).get("nickname");
    }
}
