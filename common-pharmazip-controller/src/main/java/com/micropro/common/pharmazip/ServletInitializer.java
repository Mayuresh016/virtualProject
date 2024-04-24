package com.micropro.common.pharmazip;

import java.sql.Timestamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.context.WebApplicationContext;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CommonPharmazipControllerApplication.class);
	}

	@Override
	protected WebApplicationContext run(SpringApplication application) {
		WebApplicationContext context = (WebApplicationContext) application.run();

		System.out.println(new Timestamp(System.currentTimeMillis()).toString()
				+ " --> [common-pharmazip-controller-1.0] Started Successfully");
		return context;
	}

}