package com.micropro.common.pharmazip;

import java.sql.Timestamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommonPharmazipControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonPharmazipControllerApplication.class, args);

		System.out.println(new Timestamp(System.currentTimeMillis()).toString()
				+ " --> [common-pharmazip-controller-1.0] Started Successfully");
	}

}