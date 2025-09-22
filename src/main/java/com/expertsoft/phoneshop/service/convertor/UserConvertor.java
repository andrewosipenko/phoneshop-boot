package com.expertsoft.phoneshop.service.convertor;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.expertsoft.phoneshop.persistence.model.UserInfo;
import com.expertsoft.phoneshop.service.dto.UserDto;

@Component
public class UserConvertor implements Converter<UserInfo, UserDto> {

    @Override
    @Nullable
    public UserDto convert(UserInfo source) {
        UserDto userDto = new UserDto();
        userDto.setCompany(source.getCompany());
        userDto.setAvatarUrl(source.getAvatar_url());
        userDto.setLocation(source.getLocation());
        userDto.setLogin(source.getLogin());
        return userDto;
    }

}
