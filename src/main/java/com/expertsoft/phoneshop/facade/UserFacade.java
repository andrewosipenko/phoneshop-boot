package com.expertsoft.phoneshop.facade;

import com.expertsoft.phoneshop.data.UserData;
import com.expertsoft.phoneshop.persistence.model.UserAccount;
import com.expertsoft.phoneshop.service.UserAccountService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserFacade {

    @Resource
    private UserAccountService userAccountService;
    @Resource
    private Converter<UserAccount, UserData> userDataConverter;

    public Page<UserData> getUsersPage(int page) {
        return userAccountService.getUsersPage(page).map(userDataConverter::convert);
    }
}
