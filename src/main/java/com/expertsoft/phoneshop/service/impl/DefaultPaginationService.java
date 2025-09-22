package com.expertsoft.phoneshop.service.impl;

import com.expertsoft.phoneshop.PhoneShopPaginationConfiguration;
import com.expertsoft.phoneshop.persistence.repository.PhoneRepository;
import com.expertsoft.phoneshop.service.PaginationService;
import com.expertsoft.phoneshop.service.dto.PaginationDto;

import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DefaultPaginationService implements PaginationService {
    @Resource
    private PhoneRepository phoneRepository;
    @Resource
    private PhoneShopPaginationConfiguration paginationConfiguration;
    @Resource
    private Converter<String, Sort> sortConvertor;

    public PaginationDto createPagination(Page<?> totalResult, Integer currentPage) {
        PaginationDto paginationDto = new PaginationDto();
        paginationDto.setCurrentPage(currentPage);
        paginationDto.setTotalPages(totalResult.getTotalPages());
        paginationDto.setHasLeftArrow(currentPage != 1);
        paginationDto.setHasRightArrow(currentPage != paginationDto.getTotalPages());
        return paginationDto;
    }

    public PageRequest getPageRequest(Integer oneBasedIndex, String sort) {
        Sort sorting = sortConvertor.convert(sort);
        Long count = phoneRepository.count();
        return PageRequest.of(oneBasedIndex - 1,
                Math.toIntExact(count) / paginationConfiguration.getPlpMaxPages(), sorting);
    }

    public void setPhoneRepository(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public void setPaginationConfiguration(PhoneShopPaginationConfiguration paginationConfiguration) {
        this.paginationConfiguration = paginationConfiguration;
    }

    public void setSortConvertor(Converter<String, Sort> sortConvertor) {
        this.sortConvertor = sortConvertor;
    }

}
