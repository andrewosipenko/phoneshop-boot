package com.expertsoft.phoneshop.controller.page;

import com.expertsoft.phoneshop.service.PaginationService;
import com.expertsoft.phoneshop.service.PhoneService;
import com.expertsoft.phoneshop.service.dto.PhoneDto;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

import static com.expertsoft.phoneshop.PhoneShopConstants.PHONES_PATH;

@Controller
@RequestMapping(PHONES_PATH)
public class PhoneListPageController {

    private static final String PHONE_LIST_PAGE = "phoneListPage";
    private static final String PHONES = "phones";
    private static final String PAGINATION_INFO = "paginationInfo";

    @Resource
    private PhoneService phoneService;
    @Resource
    private PaginationService paginationService;

    @GetMapping
    public String getPhoneList(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        Page<PhoneDto> phonesPage = phoneService.getPhonesPage(page);
        model.addAttribute(PHONES, phonesPage);
        model.addAttribute(PAGINATION_INFO, paginationService.createPagination(phonesPage, page));
        return PHONE_LIST_PAGE;
    }
}
