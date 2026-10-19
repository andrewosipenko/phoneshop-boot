package com.expertsoft.phoneshop.controller.page;

import com.expertsoft.phoneshop.config.PagesConfiguration;
import com.expertsoft.phoneshop.data.UserData;
import com.expertsoft.phoneshop.facade.UserFacade;
import com.expertsoft.phoneshop.service.PaginationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

import static com.expertsoft.phoneshop.PhoneShopConstants.USER_LIST_PATH;

@Controller
@RequestMapping(USER_LIST_PATH)
public class UserListPageController {
    private static final String USER_LIST_PAGE = "admin/userListPage";

    @Resource
    private UserFacade userFacade;
    @Resource
    private PagesConfiguration pagesConfiguration;
    @Resource
    private PaginationService paginationService;

    @GetMapping
    public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        int requestedPage = Math.max(1, page);
        Pageable pageable = PageRequest.of(requestedPage - 1, pagesConfiguration.getPlpMaxProducts(), Sort.by("id"));
        Page<UserData> users = userFacade.getUsersPage(page);
        model.addAttribute("users", users);
        model.addAttribute("pagination", paginationService.createPaginationDataForUserList(users, requestedPage));
        return USER_LIST_PAGE;
    }
}
