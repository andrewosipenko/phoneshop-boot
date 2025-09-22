package com.expertsoft.phoneshop.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.expertsoft.phoneshop.PhoneShopPaginationConfiguration;
import com.expertsoft.phoneshop.persistence.repository.PhoneRepository;
import com.expertsoft.phoneshop.service.dto.PaginationDto;

@ExtendWith(MockitoExtension.class)
public class DefaultPaginationServiceTest {
    private static final int PAGE_COUNT = 2;
    @Mock
    private PhoneRepository phoneRepository;
    @Mock
    private PhoneShopPaginationConfiguration paginationConfiguration;
    @Mock
    private Converter<String, Sort> sortConvertor;
    @Mock
    private Page<?> page;

    private DefaultPaginationService paginationService = new DefaultPaginationService();

    @BeforeEach
    public void setUp() {
        paginationService.setPaginationConfiguration(paginationConfiguration);
        paginationService.setSortConvertor(sortConvertor);
        paginationService.setPhoneRepository(phoneRepository);
    }

    @Test
    public void shouldCreatePaginationWithLeftArrow() {
        when(page.getTotalPages()).thenReturn(PAGE_COUNT);

        PaginationDto paginationDto = paginationService.createPagination(page, 2);

        assertTrue(paginationDto.isHasLeftArrow());
        assertFalse(paginationDto.isHasRightArrow());
    }

    @Test
    public void shouldCreatePaginationWithRightArrow() {
        when(page.getTotalPages()).thenReturn(PAGE_COUNT);

        PaginationDto paginationDto = paginationService.createPagination(page, 1);

        assertFalse(paginationDto.isHasLeftArrow());
        assertTrue(paginationDto.isHasRightArrow());

    }

    @Test
    public void shouldCreatePageRequest() {
        when(paginationConfiguration.getPlpMaxPages()).thenReturn(PAGE_COUNT);
        when(phoneRepository.count()).thenReturn(10L);
        when(sortConvertor.convert(any())).thenReturn(Sort.unsorted());

        PageRequest pageRequest = paginationService.getPageRequest(1, null);

        assertEquals(Sort.unsorted(), pageRequest.getSort());
        assertEquals(5, pageRequest.getPageSize());
    }
}
