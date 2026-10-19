package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.persistence.model.UserAccount;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class GithubOAuth2UserService extends DefaultOAuth2UserService {

    @Resource
    private UserAccountService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        userService.registerOrUpdate(toClient(oAuth2User.getAttributes()));
        return oAuth2User;
    }

    UserAccount toClient(Map<String, Object> attributes) {
        UserAccount user = new UserAccount();
        user.setLogin((String) attributes.get("login"));
        user.setName((String) attributes.get("name"));
        user.setBio((String) attributes.get("bio"));
        user.setAvatarUrl((String) attributes.get("avatar_url"));
        user.setLocation((String) attributes.get("location"));
        user.setCompany((String) attributes.get("company"));
        return user;
    }
}
