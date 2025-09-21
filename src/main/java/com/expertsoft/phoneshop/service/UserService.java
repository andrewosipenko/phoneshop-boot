package com.expertsoft.phoneshop.service;

import java.util.List;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import com.expertsoft.phoneshop.service.dto.UserDto;

public interface UserService {

    public void saveUser(OAuth2AuthenticationToken oAuth2AuthenticationToken);

    public List<UserDto> getAllUsers();

    public UserDto getCurrentUser();
}
