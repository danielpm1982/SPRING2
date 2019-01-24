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
Adding to last examples, this REST API program simply implements JPA connection and a Client entity to be managed by a simple CRUD, according to the
path/pathVariable and http method used at the request call. All the rest is the same.
Differently from traditional Spring (with no SpringBoot layer), by importing the initializr project, with the POM including the starter dependencies
for the JPA connection, there is no need to use any java or xml configuration file, unless other custom confs must be done (as when using Security).
This way, the connection and JPA beans are created and instantiated automatically: DataSource, EntityManager (instead of SessionFactory) and 
TransactionManager. And can therefore be injected at the DAO and Service classes... and the Service class at the RESTController one.
For the endpoints, see the RestController class.
*/
