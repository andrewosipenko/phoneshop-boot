package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.config.PlpConfiguration;
import com.expertsoft.phoneshop.data.PaginationData;
import com.expertsoft.phoneshop.data.PhoneData;
import com.expertsoft.phoneshop.persistence.model.Phone;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaginationService {

    @Resource
    private PlpConfiguration plpConfiguration;

    public PaginationData createPaginationData(Page<PhoneData> page, int requestedPage) {
        return new PaginationData(requestedPage, page.getTotalPages(), plpConfiguration.getPlpMaxPages());
    }


}
