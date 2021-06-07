package com.projetosemana3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EntityScan(basePackages = {"com.projetosemana3"})
@ComponentScan(basePackages = {"com.projetosemana3.*"})
@EnableJpaRepositories(basePackages = {"com.accenture.projetosemana3.repository"})
@EnableTransactionManagement
@RestController
@EnableAutoConfiguration
public class Projetosemana3Application {

	public static void main(String[] args) {
		SpringApplication.run(Projetosemana3Application.class, args);
		System.out.println("hello wooooooooooooorld, agora rodou em. bora, pracima meu mano");
	}

}
