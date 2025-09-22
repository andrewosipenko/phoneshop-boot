package com.expertsoft.phoneshop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "phoneshop")
public class PhoneShopPaginationConfiguration {
    private Integer plpMaxPages;

    public Integer getPlpMaxPages() {
        return plpMaxPages;
    }

    public void setPlpMaxPages(Integer plpMaxPages) {
        this.plpMaxPages = plpMaxPages;
    }

}
