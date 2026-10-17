package com.expertsoft.phoneshop.controller.page;

import com.expertsoft.phoneshop.config.PlpConfiguration;
import com.expertsoft.phoneshop.persistence.model.Phone;
import com.expertsoft.phoneshop.service.PaginationService;
import com.expertsoft.phoneshop.service.PhoneService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private static final String PAGE_PARAM = "page";
    private static final String PAGINATION = "pagination";
    @Resource
    private PhoneService phoneService;

    @Resource
    private PaginationService paginationService;

    @Resource
    private PlpConfiguration plpConfiguration;

    @GetMapping
    public String getPhoneList(@RequestParam(name = PAGE_PARAM, defaultValue = "1") int page,
                               Model model) {
        int requestedPage = Math.max(1, page);
        Pageable pageable = PageRequest.of(requestedPage - 1, plpConfiguration.getPlpMaxProducts());
        Page<Phone> phonesPage = phoneService.getPhonesPage(pageable);
        if (phonesPage.getTotalPages() > 0 && requestedPage > phonesPage.getTotalPages()) {
            return "redirect:" + PHONES_PATH + "?" + PAGE_PARAM + "=" + phonesPage.getTotalPages();
        }
        model.addAttribute(PHONES, phonesPage);
        model.addAttribute(PAGINATION, paginationService.createPaginationData(phonesPage, requestedPage));
        return PHONE_LIST_PAGE;
    }
}
