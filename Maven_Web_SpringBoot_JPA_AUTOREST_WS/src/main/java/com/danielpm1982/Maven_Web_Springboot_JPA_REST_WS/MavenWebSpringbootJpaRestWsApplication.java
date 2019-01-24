package com.danielpm1982.Maven_Web_Springboot_JPA_REST_WS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MavenWebSpringbootJpaRestWsApplication {
	public static void main(String[] args) {
		SpringApplication.run(MavenWebSpringbootJpaRestWsApplication.class, args);
	}
}

/*
When using spring-boot-starter-data-jpa and spring-boot-starter-data-rest dependencies,
and for a basic, inbuilt and automatic DAO and Rest/Service construction, you only need to set
the extension of the JPA Repository interface, setting the types for the entity and id (PK),
and everything else is automatically done. Also gotta have created the entity class and import
the dependencies at the POM. And, if wished, set any configuration at the application.properties
file, as the base path for the Spring Data Rest endpoints.
*/
