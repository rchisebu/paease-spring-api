package com.starlabs.PaEase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PaEaseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaEaseApiApplication.class, args);
	}

}
