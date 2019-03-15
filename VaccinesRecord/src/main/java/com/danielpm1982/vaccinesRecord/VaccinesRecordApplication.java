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
Please run, at MySQL Workbench, one of the two sql scheme backups available at resources/dataBaseSchemes. 

The initialScheme is populated only with the User ROLE types.

The populatedExampleScheme is populated with the ROLE types and with User, Vaccine, Patient and 
VaccineAdministration data.

If the example scheme data is used, the User data for login are as follows:

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
with a WebService REST API. See the RESTController if you wish to know the endpoints for accessing
all API via Postman or other app. Through the JSP/HTML view, only part of the API is visible
at the links (GET requests)... but other endpoints (POST, PUT, DELETE) are implemented as well. 
*/

/*
BCrypt with salt 15 is used as encryption for all passwords. Alter the salt if you wish faster 
responses with lower security.
*/
