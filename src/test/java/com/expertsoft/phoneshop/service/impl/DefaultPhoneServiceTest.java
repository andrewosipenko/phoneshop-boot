package com.expertsoft.phoneshop.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.expertsoft.phoneshop.persistence.model.Phone;
import com.expertsoft.phoneshop.persistence.repository.PhoneRepository;
import com.expertsoft.phoneshop.service.PaginationService;
import com.expertsoft.phoneshop.service.dto.PhoneDto;

@ExtendWith(MockitoExtension.class)
public class DefaultPhoneServiceTest {
    private static final String QUERY = "query";
    @Mock
    private PaginationService paginationService;
    @Mock
    private PhoneRepository phoneRepository;
    @Mock
    private Converter<Phone, PhoneDto> phoneConverter;
    @Mock
    private Phone phone;
    @Mock
    private PhoneDto phoneDto;
    @Mock
    private Page<Phone> pagePhone;
    @Mock
    private Page<PhoneDto> pagePhoneDto;
    @Mock
    private PageRequest pageRequest;

    private DefaultPhoneService defaultPhoneService = new DefaultPhoneService();

    @BeforeEach
    public void setUp() {
        defaultPhoneService.setPhoneRepository(phoneRepository);
        defaultPhoneService.setPaginationService(paginationService);
        defaultPhoneService.setPhoneConverter(phoneConverter);
    }

    @Test
    void shouldGetPhoneById() {
        when(phoneRepository.findOneById(anyLong())).thenReturn(Optional.of(phone));
        when(phoneConverter.convert(phone)).thenReturn(phoneDto);

        Optional<PhoneDto> result = defaultPhoneService.getPhoneById(1L);

        assertEquals(phoneDto, result.get());
    }

    @Test
    void shouldGetPhonePage() {
        when(pagePhone.map(any(Function.class))).thenReturn(pagePhoneDto);
        when(paginationService.getPageRequest(1, "sort")).thenReturn(pageRequest);
        when(phoneRepository.findAll(any(PageRequest.class))).thenReturn(pagePhone);

        var result = defaultPhoneService.getPhonesPage(1, "sort");

        assertEquals(pagePhoneDto, result);
    }

    @Test
    void shouldGetWithoutQueryRequest() {
        when(paginationService.getPageRequest(1, null)).thenReturn(pageRequest);
        when(phoneRepository.findAllByPriceBetween(any(), any(), any())).thenReturn(pagePhone);

        defaultPhoneService.getPhonesSearchPage(1, null, null, null, null);

        verify(phoneRepository).findAllByPriceBetween(eq(BigDecimal.ZERO), eq(BigDecimal.valueOf(Double.MAX_VALUE)),
                any());
    }

    @Test
    void shouldGetQueryRequest() {
        when(paginationService.getPageRequest(1, null)).thenReturn(pageRequest);
        when(phoneRepository.findAllByBrandContainingAndModelContainingAndPriceBetween(eq(QUERY), any(), any(), any()))
                .thenReturn(pagePhone);

        defaultPhoneService.getPhonesSearchPage(1, QUERY, null, null, null);

        verify(phoneRepository).findAllByBrandContainingAndModelContainingAndPriceBetween(eq(QUERY),
                eq(BigDecimal.ZERO),
                eq(BigDecimal.valueOf(Double.MAX_VALUE)),
                any());
    }
}
