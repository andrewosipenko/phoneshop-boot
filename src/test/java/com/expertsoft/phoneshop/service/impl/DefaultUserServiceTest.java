package com.expertsoft.phoneshop.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;

import com.expertsoft.phoneshop.persistence.model.UserInfo;
import com.expertsoft.phoneshop.persistence.repository.UserInfoRepository;
import com.expertsoft.phoneshop.service.SecuritySessionService;
import com.expertsoft.phoneshop.service.dto.UserDto;

@ExtendWith(MockitoExtension.class)
public class DefaultUserServiceTest {
    private static final String USERNAME = "username";
    @Mock
    private SecuritySessionService securitySessionService;
    @Mock
    private UserInfoRepository userInfoRepository;
    @Mock
    @Resource
    private Converter<UserInfo, UserDto> userConverter;
    @Mock
    private UserInfo userInfo1;
    @Mock
    private UserInfo userInfo2;
    @Mock
    private UserDto userDto;

    private DefaultUserService userService = new DefaultUserService();

    @BeforeEach
    public void setUp() {
        userService.setUserInfoRepository(userInfoRepository);
        userService.setSecuritySessionService(securitySessionService);
        userService.setUserConverter(userConverter);
    }

    @Test
    public void shouldReturnAllUsers() {
        when(userInfoRepository.findAll()).thenReturn(List.of(userInfo1, userInfo2));
        when(userConverter.convert(any())).thenReturn(userDto);

        List<UserDto> result = userService.getAllUsers();

        assertEquals(2, result.size());
    }

    @Test
    public void shouldCurrentUser() {
        when(securitySessionService.extractUsername()).thenReturn(USERNAME);
        when(userInfoRepository.getUserByLogin(eq(USERNAME))).thenReturn(Optional.of(userInfo1));
        when(userConverter.convert(any())).thenReturn(userDto);

        UserDto result = userService.getCurrentUser();

        assertEquals(userDto, result);
    }

    @Test
    public void shouldSaveUser() {
        userService.saveUser(userInfo1);

        verify(userInfoRepository).save(userInfo1);
    }
}
