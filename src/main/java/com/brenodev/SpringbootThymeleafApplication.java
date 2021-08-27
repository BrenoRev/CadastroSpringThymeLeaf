package com.brenodev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebMvc
public class SpringbootThymeleafApplication implements WebMvcConfigurer{
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootThymeleafApplication.class, args);
	}
	
	// MAPEANDO O LOGIN DO SPRING SECURITY E REDIRECIONAR PARA NOSSA PÁGINA DE LOGIN
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("/login");
		registry.setOrder(Ordered.LOWEST_PRECEDENCE);
	}

}
