package com.tsd.add.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.tsd.add.registration.repo")
@EntityScan(basePackages = "com.tsd.add.registration.entity")
public class AddressmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressmanagementApplication.class, args);
	}
	
	@Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
