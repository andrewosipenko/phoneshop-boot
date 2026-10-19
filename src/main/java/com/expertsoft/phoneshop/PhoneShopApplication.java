package com.expertsoft.phoneshop;

import com.expertsoft.phoneshop.config.PagesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties(PagesConfiguration.class)
public class PhoneShopApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PhoneShopApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PhoneShopApplication.class, args);
	}

}
