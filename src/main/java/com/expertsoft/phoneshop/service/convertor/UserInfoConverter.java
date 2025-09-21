package com.expertsoft.phoneshop.service.convertor;

import java.util.Optional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;

import com.expertsoft.phoneshop.persistence.model.UserInfo;

@Component
public class UserInfoConverter implements Converter<OAuth2AuthenticationToken, UserInfo> {

    @Override
    @Nullable
    public UserInfo convert(OAuth2AuthenticationToken source) {
        UserInfo userInfo = new UserInfo();
        Optional.ofNullable(source.getPrincipal().getAttribute("login"))
                .map(Object::toString)
                .ifPresent(userInfo::setLogin);
        Optional.ofNullable(source.getPrincipal().getAttribute("avatar_url"))
                .map(Object::toString)
                .ifPresent(userInfo::setAvatar_url);
        Optional.ofNullable(source.getPrincipal().getAttribute("bio"))
                .map(Object::toString)
                .ifPresent(userInfo::setBio);
        Optional.ofNullable(source.getPrincipal().getAttribute("location"))
                .map(Object::toString)
                .ifPresent(userInfo::setLocation);
        return userInfo;
    }

}
