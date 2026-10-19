package com.expertsoft.phoneshop.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.expertsoft.phoneshop.PhoneShopConstants.ADMIN_PATH;

@Controller
@RequestMapping(ADMIN_PATH)
public class AdminPageController {
    private static final String ADMIN_PAGE = "admin/adminPanelPage";
    @GetMapping
    public String getAdminPage() {
        return ADMIN_PAGE;
    }

}
