package com.danielpm1982.Maven_Web_Springboot_JPA_AUTOREST_WS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MavenWebSpringbootJpaRestWsApplication {
	public static void main(String[] args) {
		SpringApplication.run(MavenWebSpringbootJpaRestWsApplication.class, args);
	}
}

/*
When using spring-boot-starter-data-jpa AND spring-boot-starter-data-rest dependencies,
and for a basic, inbuilt and automatic DAO and Rest/Service construction, you only need to set
the extension of the JPA Repository interface, including the types for the entity and id (PK),
and everything else is automatically done. All interfaces (for each existing entity) that
extend the JPA Repository interface are mapped, as a DAO, for the automatically created and hidden 
RESTController/Service, and the apis are turned available to the user to access via http requests. 
Also, it must be created the entity(ies) class(es) and dependencies must have been imported at 
the POM. And, if wished, configurations can be set at the application.properties file, e.g. the 
base path for the Spring Data Rest endpoints. Or set as an annotation at that interface declaration, 
e.g. a different endpoint other than the simple plural form of the entity. See the 
ClientDAOJPARepositoryInterface at this project, as an example.
*/

/*
E.g. Valid endpoint paths for this api regarding "clients":
http://localhost:8080/api/clients
http://localhost:8080/api/clients?sort=name,desc
http://localhost:8080/api/clients?page=0&size=3&sort=birthDate,desc

Other links are automatically turned available at the returning .json response in the
"_links" object part of the file content. For example, if a page size is inferior to the
number of results, there would be more than one page to show, and links for all pages
would appear in this "_links" object part of the file. Or the user would put it directly
in the path field, as http request attributes: page=0, page=1, page=2, and so on. 
At the "page" object of the same .json returning file content, for each response you'll 
have: the size of the pages, the totalElements of each page, the total number of pages 
and the number of the current page.
E.g.: "page": {
          "size": 3,
          "totalElements": 3,
          "totalPages": 1,
          "number": 0
		}
*/
