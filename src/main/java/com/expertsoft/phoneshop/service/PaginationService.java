package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.service.dto.PaginationDto;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PaginationService {

    public PaginationDto createPagination(Page<?> totalResult, Integer currentPage) {
        PaginationDto paginationDto = new PaginationDto();
        paginationDto.setCurrentPage(currentPage);
        paginationDto.setTotalPages(totalResult.getTotalPages());
        paginationDto.setHasLeftArrow(currentPage != 1);
        paginationDto.setHasRightArrow(currentPage != paginationDto.getTotalPages());
        return paginationDto;
    }

}
