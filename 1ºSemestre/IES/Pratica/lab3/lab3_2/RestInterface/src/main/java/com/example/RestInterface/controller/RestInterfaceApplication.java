package com.example.RestInterface.controller;

import com.example.RestInterface.repository.EmployeeRepository;
import com.example.RestInterface.model.Employee;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.example.RestInterface.model")
@EnableJpaRepositories("com.example.RestInterface.repository")
@SpringBootApplication
public class RestInterfaceApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestInterfaceApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(EmployeeRepository repository) {
		return (args) -> {
		// save a few customers
		repository.save(new Employee("Jack", "Bauer", "jack@ua.pt"));
		repository.save(new Employee("Chloe", "O'Brian", "chloe@ua.pt"));
		repository.save(new Employee("Kim", "Bauer", "kim@ua.pt"));
		repository.save(new Employee("David", "Palmer", "david@ua.pt"));
		repository.save(new Employee("Michelle", "Dessler", "michelle@ua.pt"));
 		};
	}
}
