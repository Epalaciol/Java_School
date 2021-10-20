package com.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SchoolApplication {

	@RequestMapping("/")
	public String home() {
		return "Hola Mundo";
	}

	@RequestMapping("/{cliente}")
	public String clientePrueba(@PathVariable("cliente") String cliente){
		return  "Hola "+ cliente+ " Bienvenido a la application";
	}
	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

}
