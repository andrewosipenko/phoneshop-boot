package com.expertsoft.phoneshop.facade;


import com.expertsoft.phoneshop.converter.PhoneToPhoneDataConverter;
import com.expertsoft.phoneshop.data.PhoneData;
import com.expertsoft.phoneshop.service.PhoneService;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import javax.annotation.Resource;

@Component
public class PhoneFacade {
    @Resource
    private PhoneService phoneService;
    @Resource
    private PhoneToPhoneDataConverter phoneDataConverter;


    public PhoneData getPhone(Long id) {
        return phoneService.getPhoneById(id)
                .map(phoneDataConverter::convert)
                .orElseThrow(() -> new NotFoundException(id.toString()));
    }
}
