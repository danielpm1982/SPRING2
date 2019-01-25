package com.danielpm1982.Maven_Web_SpringBoot_Thymeleaf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Maven_Web_SpringBoot_ThymeleafApplication {
	public static void main(String[] args) {
		SpringApplication.run(Maven_Web_SpringBoot_ThymeleafApplication.class, args);
	}
}

/*
This is an extremely simple Thymeleaf SpringBoot Maven app, which merely shows a dateTime text.
When using at the POM the thymeleaf dependency (spring-boot-starter-thymeleaf),
all strings returned from controllers are mapped to the thymeleaf templates at:
resources/templates... in this case, an html template, although other types are 
supported. Instead of .jsp, the mapping is to .html files. 
For more on Thymeleaf see: https://www.thymeleaf.org/ .
*/
