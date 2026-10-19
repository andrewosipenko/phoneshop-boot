package com.expertsoft.phoneshop.converter;

import com.expertsoft.phoneshop.data.UserData;
import com.expertsoft.phoneshop.persistence.model.UserAccount;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class UserAccountToUserDataConverter implements Converter<UserAccount, UserData> {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public UserData convert(UserAccount source) {
        UserData target = new UserData();
        target.setLogin(source.getLogin());
        target.setLocation(source.getLocation());
        target.setRegisteredAt(source.getRegisteredAt() == null ? "unknown" : source.getRegisteredAt().format(FORMAT));
        return target;
    }
}
