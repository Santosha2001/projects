package com.vmanager.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.vmanager")
public class BeanConfiguration {

	public BeanConfiguration() {
		System.out.println("BeanConfiguration created.");
	}

	@Bean
	public ViewResolver viewResolver() {
		ViewResolver resolver = new InternalResourceViewResolver("/", ".jsp");
		return resolver;
	}

	// Implementation of the EntityManagerFactory interface.
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

		return new LocalContainerEntityManagerFactoryBean();
	}
}
