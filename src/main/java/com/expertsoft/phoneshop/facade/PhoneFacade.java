package com.expertsoft.phoneshop.facade;


import com.expertsoft.phoneshop.converter.PhoneToPhoneDataConverter;
import com.expertsoft.phoneshop.data.PhoneData;
import com.expertsoft.phoneshop.data.SortData;
import com.expertsoft.phoneshop.service.PhoneService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Component
public class PhoneFacade {
    @Resource
    private PhoneService phoneService;
    @Resource
    private PhoneToPhoneDataConverter phoneDataConverter;


    public Page<PhoneData> getPhonesPage(int page, SortData sortData) {
        return phoneService.getPhonesPage(page, sortData).map(phoneDataConverter::convert);
    }

    public Page<PhoneData> getPhonesSearchPage(int page, String query, BigDecimal priceFrom, BigDecimal priceTo, SortData sortData) {
        return phoneService.getPhonesSearchPage(page, query, priceFrom, priceTo, sortData).map(phoneDataConverter::convert);
    }

    public PhoneData getPhone(Long id) {
        return phoneService.getPhoneById(id)
                .map(phoneDataConverter::convert)
                .orElseThrow(() -> new NotFoundException(id.toString()));
    }
}
