package com.expertsoft.phoneshop.service.impl;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.expertsoft.phoneshop.persistence.model.UserInfo;
import com.expertsoft.phoneshop.persistence.repository.UserInfoRepository;
import com.expertsoft.phoneshop.service.UserService;
import com.expertsoft.phoneshop.service.dto.UserDto;
import com.expertsoft.phoneshop.strategy.LoginExtractionStrategy;

@Service
public class DefaultUserService implements UserService {
    private static final UserDto GUEST = new UserDto("guest");

    @Resource
    private Converter<OAuth2AuthenticationToken, UserInfo> userInfoConverter;
    @Resource
    private Converter<UserInfo, UserDto> userConverter;
    @Resource
    private UserInfoRepository userInfoRepository;
    @Resource
    private List<LoginExtractionStrategy> loginExtractionStrategies;

    @Override
    public void saveUser(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        UserInfo userInfo = userInfoConverter.convert(oAuth2AuthenticationToken);
        userInfoRepository.save(userInfo);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userInfoRepository.findAll().stream()
                .map(userConverter::convert)
                .toList();
    }

    @Override
    public UserDto getCurrentUser() {
        UserDto result = GUEST;
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String login = getLogin(authentication);
            return userInfoRepository.getUserByLogin(login)
                    .map(userConverter::convert)
                    .orElse(result);
        }
        return result;
    }

    private String getLogin(Authentication authentication) {
        return loginExtractionStrategies.stream()
                .filter(strategy -> strategy.isApplicable(authentication))
                .findFirst()
                .map(strategy -> strategy.extractLogin(authentication))
                .orElse("guest");
    }

}
