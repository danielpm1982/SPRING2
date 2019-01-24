package com.danielpm1982.Maven_Web_SpringBoot_REST_WS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MavenWebSpringBootRestWsApplication {
	public static void main(String[] args) {
		SpringApplication.run(MavenWebSpringBootRestWsApplication.class, args);
	}
}

/*This is a basic Maven SpringBoot app example*/

/*
For SpringBoot general info: advantages over Spring without SpringBoot, initialzr site https://start.spring.io/ - base project 
creation and import, starting main class (@SpringBootApplication annotation), POM parent dependencies configuration 
(dependencies versions set by default) and starter dependencies (groups of common dependencies).

For app restarting automation, use Devtools (dependency): automatic server restarting when code or config properties change.

For app management, use the Actuator: several endpoints for application management, including "actuator/info" and 
"actuator/health". Some properties can be changed, as the port and base-path, using the application-properties file.
See available default properties at (look for actuator properties):
https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html

For running from the command line, use the following commands accordingly:
1) "sudo ./mvnw package": for packing the whole project into a .jre file (saved in the target folder). 
2) "sudo java -jar target/Maven_Web_SpringBoot_REST_WS.jar" or "sudo ./mvnw spring-boot:run" 
PS.: for setting the JAVA_HOME variable value in Ubuntu use the /etc/environment file, adding
the property and value (e.g. JAVA_HOME="/opt/jdk-10.0.2/"), and then resource the environment file
to load the new properties. Use echo $JAVA_HOME for confirming this property has been set properly.
PS.: If security is to be used, basic Authentication/Authorization: username = admin , 
password = admin (as set at the properties file) - uncomment it. 
Uncomment also the security dependency import at the POM file. If custom Security is desired,
rather than the default one, create and use Java config files as usual for setting it.
PS.: See application.properties file for info about default properties, including the
management server port (actuator port) and server port (app services port), for ex.
PS.: As the SpringBoot app has an in-built http server (tomcat), it can be run as a standalone app,
using the commands above, or be exported and later deployed at the server as a war file. 

For configuration, a default file is the application.properties file at the resources folder. 
It can be used for setting default property values as well as custom property values, 
these which later can be injected at fields using @Value("${propertyName}").
See available default properties at:
https://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html

For testing this SpringBoot Restfull Web Service app at the command line try (or use a browser or Postman):
curl -i -H "Accept: application/json" -w "\n\n" http://localhost:9191/MavenWebSpringBootRESTWS/info
or 
curl -i -H "Accept: application/json" -w "\n\n" http://localhost:9090/MavenWebSpringBootRESTWS/
*/
