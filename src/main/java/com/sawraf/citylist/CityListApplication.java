package com.sawraf.citylist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class CityListApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityListApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		// Registering accept-header Locale Resolver with default Locale.US
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);

		return localeResolver;
	}
}
