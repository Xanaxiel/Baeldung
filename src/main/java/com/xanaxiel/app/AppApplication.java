package com.xanaxiel.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableWebFlux
public class AppApplication {

	public static void main(String[] args) {
		run(AppApplication.class, args);
	}

}
