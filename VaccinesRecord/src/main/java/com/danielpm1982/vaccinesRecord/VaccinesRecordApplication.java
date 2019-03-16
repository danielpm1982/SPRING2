package com.danielpm1982.vaccinesRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VaccinesRecordApplication {
	public static void main(String[] args) {
		SpringApplication.run(VaccinesRecordApplication.class, args);
	}
}


/*
For running this app:

1) change, at the resources/application.properties file, the targetStaticResourceAbsolutePath property to a value that corresponds to the folder 
structure you put the project into (two example base paths are given, one for Linux and another for Windows);

2) one can open it at an IDE like Eclipse and run, as a java application, the main class file: VaccinesRecordApplication.java.

3) or, for running outside the IDE, one could use the .jar file, which already contains the spring-boot in-built web container (tomcat/apache).
If folder structure is changed, regenerate the .jar with ./mvnw package before using it. Then, for running, use: ./mvnw spring-boot:run. 
The folder of the Maven command is VaccinesRecord/ . The folder of the .jar is VaccinesRecord/target/ ; and the temp should be automatically 
created inside the folder VaccinesRecord/target/classes/static/ .

4) Because physical paths have been used for the image files, using java -jar alone wouldn't work without further adaptation (so ./mvnw should be 
used instead).
*/

/*
Before running for the first time, and for generating the DB at MySQL, please run, at the Workbench, one of the two SQL scheme backups available 
at resources/dataBaseSchemes.

The initialScheme is populated only with the User ROLE types (required!).

The populatedExampleScheme is populated with the ROLE types (required) and with User, Vaccine, Patient and 
VaccineAdministration data (optional example data).

The example data, at the second option right above, is as follows:

user1
id: 1
userName: user1
password: 123
firstName: user1FirstName
lastName: user1LastName
email: user1@user.com
roles: ROLE_USER (default)

patient1
id: 2
userName: patient1
password: 123
firstName: patient1FirstName
lastName: patient1LastName
email: patient1@patient.com
roles: ROLE_USER, ROLE_PATIENT

manager1
id: 3
userName: manager1
password: 123
firstName: manager1FirstName
lastName: manager1LastName
email: manager1@manager.com
roles: ROLE_USER, ROLE_MANAGER

director1
id: 4
userName: director1
password: 123
firstName: director1FirstName
lastName: director1LastName
email: director1@director.com
roles: ROLE_USER, ROLE_DIRECTOR
*/

/*
This app has two main User interfaces, one with traditional JSP/HTML pages and links and another
with WebService REST API. See the RESTController if you wish to know the endpoints for accessing
all the API via Postman or other http requester app. Through the JSP/HTML view, only part of the API 
is visible at the links (GET requests)... but other endpoints (POST, PUT, DELETE) are implemented as well. 
*/

/*
BCrypt, with salt 15, is used as encryption for all passwords. Alter the salt (for less) at the 
config/MyWebSecurityConfigurerAdapter.java file, if you wish faster responses with lower security 
(min 4, max 31 - at least 12 of salt is advisable by security experts).
*/

/*
THIS APP HAS BEEN DEVELOPED ENTIRELY AND EXCLUSIVELY BY: danielpm1982.com. 

THIS APP AND ITS SOURCE CODE ARE NOT INTENDED FOR COMMERCIAL USE, DISTRIBUTION OR MODIFICATION BY ANY MEANS. 

AND ARE BOTH AVAILABLE FOR ACADEMIC AND SELF-LEARNING PURPOSES ONLY. 

For contact, send an email to: danielpm1982.com@domainsbyproxy.com .
*/
