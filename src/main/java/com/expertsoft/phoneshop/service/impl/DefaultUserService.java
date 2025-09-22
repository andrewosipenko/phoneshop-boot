package com.expertsoft.phoneshop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import com.expertsoft.phoneshop.persistence.model.UserInfo;
import com.expertsoft.phoneshop.persistence.repository.UserInfoRepository;
import com.expertsoft.phoneshop.service.SecuritySessionService;
import com.expertsoft.phoneshop.service.UserService;
import com.expertsoft.phoneshop.service.dto.UserDto;

@Service
public class DefaultUserService implements UserService {

    @Resource
    private SecuritySessionService securitySessionService;
    @Resource
    private Converter<UserInfo, UserDto> userConverter;
    @Resource
    private UserInfoRepository userInfoRepository;

    @Override
    public void saveUser(UserInfo userInfo) {
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
        return userInfoRepository.getUserByLogin(securitySessionService.extractUsername())
                .map(userConverter::convert).get();
    }

    public void setUserConverter(Converter<UserInfo, UserDto> userConverter) {
        this.userConverter = userConverter;
    }

    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public void setSecuritySessionService(SecuritySessionService securitySessionService) {
        this.securitySessionService = securitySessionService;
    }

}
