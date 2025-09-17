package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.PhoneShopPaginationConfiguration;
import com.expertsoft.phoneshop.service.dto.PaginationDto;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PaginationService {
    @Resource
    private PhoneShopPaginationConfiguration paginationConfiguration;

    public PaginationDto createPagination(Page<?> totalResult, Integer currentPage) {
        PaginationDto paginationDto = new PaginationDto();
        paginationDto.setCurrentPage(currentPage + 1);
        paginationDto.setTotalPages(totalResult.getTotalPages());
        paginationDto.setPageSize(paginationConfiguration.getDefaultPageSize());
        paginationDto.setHasLeftArrow(currentPage != 0);
        paginationDto.setHasRightArrow(currentPage != paginationDto.getTotalPages() - 1);
        int start = getStart(currentPage);
        paginationDto.setStartPage(start + 1);
        paginationDto.setEndPage(getEnd(paginationDto, start) + 1);
        return paginationDto;
    }

    private int getEnd(PaginationDto paginationDto, int start) {
        return start + paginationConfiguration.getPlpMaxPages() - 1 < paginationDto.getTotalPages()
                ? start + paginationConfiguration.getPlpMaxPages() - 1
                : paginationDto.getTotalPages();
    }

    private int getStart(Integer currentPage) {
        int start = currentPage - (paginationConfiguration.getPlpMaxPages() - 1) / 2;
        if (start < 0) {
            start = 0;
        }
        return start;
    }

}
