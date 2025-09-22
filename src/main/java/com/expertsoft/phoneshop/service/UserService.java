package com.expertsoft.phoneshop.service;

import java.util.List;

import com.expertsoft.phoneshop.persistence.model.UserInfo;
import com.expertsoft.phoneshop.service.dto.UserDto;

public interface UserService {
    public void saveUser(UserInfo userInfo);

    public List<UserDto> getAllUsers();

    public UserDto getCurrentUser();
}
