package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.config.PagesConfiguration;
import com.expertsoft.phoneshop.data.PaginationData;
import com.expertsoft.phoneshop.data.PhoneData;
import com.expertsoft.phoneshop.data.UserData;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaginationService {

    @Resource
    private PagesConfiguration pagesConfiguration;

    public PaginationData createPaginationData(Page<PhoneData> page, int requestedPage) {
        return new PaginationData(requestedPage, page.getTotalPages(), pagesConfiguration.getPlpMaxPages());
    }

    public PaginationData createPaginationDataForUserList (Page<UserData> page, int requestedPage) {
        return new PaginationData(requestedPage, page.getTotalPages(), pagesConfiguration.getPlpMaxPages());
    }


}
