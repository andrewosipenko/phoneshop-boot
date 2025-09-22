package com.expertsoft.phoneshop.service;

import com.expertsoft.phoneshop.service.dto.PaginationDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface PaginationService {
    PaginationDto createPagination(Page<?> totalResult, Integer currentPage);

    PageRequest getPageRequest(Integer oneBasedIndex, String sort);
}
