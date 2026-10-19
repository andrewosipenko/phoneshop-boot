package com.expertsoft.phoneshop.controller.page;

import com.expertsoft.phoneshop.config.PagesConfiguration;
import com.expertsoft.phoneshop.data.PhoneData;
import com.expertsoft.phoneshop.data.SortData;
import com.expertsoft.phoneshop.facade.PhoneFacade;
import com.expertsoft.phoneshop.service.PaginationService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

import java.math.BigDecimal;

import static com.expertsoft.phoneshop.PhoneShopConstants.PHONES_PATH;

@Controller
@RequestMapping(PHONES_PATH)
public class PhoneListPageController {

    private static final String PHONE_LIST_PAGE = "phoneListPage";
    private static final String PHONES = "phones";
    private static final String PAGE_PARAM = "page";
    private static final String PAGINATION = "pagination";

    @Resource
    private PhoneFacade phoneFacade;

    @Resource
    private PaginationService paginationService;

    @Resource
    private PagesConfiguration pagesConfiguration;

    @GetMapping
    public String getPhoneList(@RequestParam(name = PAGE_PARAM, defaultValue = "1") int page,
                               @ModelAttribute SortData sortData,
                               Model model) {
        int requestedPage = Math.max(1, page);
        Page<PhoneData> phonesPage = phoneFacade.getPhonesPage(requestedPage, sortData);
        if (phonesPage.getTotalPages() > 0 && requestedPage > phonesPage.getTotalPages()) {
            return "redirect:" + PHONES_PATH + "?" + PAGE_PARAM + "=" + phonesPage.getTotalPages();
        }
        model.addAttribute(PHONES, phonesPage);
        model.addAttribute(PAGINATION, paginationService.createPaginationData(phonesPage, requestedPage));
        return PHONE_LIST_PAGE;
    }

    @GetMapping(params = "query")
    public String getSearchPage(Model model, @RequestParam("query") String query,
                         @RequestParam(value = "priceFrom", required = false) BigDecimal priceFrom,
                         @RequestParam(value = "priceTo", required = false) BigDecimal priceTo,
                         @RequestParam(value = "page", defaultValue = "1") Integer page,
                         @ModelAttribute SortData sortData) {
        int requestedPage = Math.max(1, page);
        Page<PhoneData> phonesPage = phoneFacade.getPhonesSearchPage(requestedPage, query, priceFrom, priceTo, sortData);
        model.addAttribute(PHONES, phonesPage);
        model.addAttribute(PAGINATION, paginationService.createPaginationData(phonesPage, page));
        return PHONE_LIST_PAGE;
    }

}
