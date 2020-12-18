package com.finastra.mediastra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@ComponentScan("com.finastra.mediastra")
@EntityScan("com.finastra.mediastra")
@EnableJpaRepositories("com.finastra.mediastra")
@SpringBootApplication
public class MediastraApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediastraApplication.class, args);
	}

}
