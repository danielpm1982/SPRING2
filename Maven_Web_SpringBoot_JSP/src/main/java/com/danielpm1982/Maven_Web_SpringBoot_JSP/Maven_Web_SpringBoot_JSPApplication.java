package com.danielpm1982.Maven_Web_SpringBoot_JSP;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Maven_Web_SpringBoot_JSPApplication {
	public static void main(String[] args) {
		SpringApplication.run(Maven_Web_SpringBoot_JSPApplication.class, args);
	}
}

/*
For working with SpringBoot and JSP, you only need to add the "tomcat-embed-jasper" dependency
at the POM, and set the spring.mvc.view.prefix and spring.mvc.view.sufix at the properties file.
The jsp file should be put inside webapp/WEB-INF/view. The css file at resources/static/css 
folder. The img files at resources/static/img.
For some problems, using war packaging, instead of the default jar, should solve the problem.
See the specs at:
https://spring.io/projects/spring-boot
*/
