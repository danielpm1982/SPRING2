package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB.config;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource securityDataSource;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").hasRole("USER")
		.antMatchers("/controller/administration/**").hasAnyRole("ADMIN", "MANAGER")
		.antMatchers("/controller/directory/**").hasRole("DIRECTOR")
		.antMatchers("/controller/ceo/**").hasRole("CEO").and()
		.formLogin().loginPage("/controller/customLoginPage").loginProcessingUrl("/controller/customLoginPageResult").permitAll().and()
		.logout().permitAll().and()
		.exceptionHandling().accessDeniedPage("/controller/accessDenied");
	}
}

/*
This config class enables and configurates WebSecurity.

Authentication is set by overriding the configure(AuthenticationManagerBuilder auth) method 
of WebSecurityConfigurerAdapter, creating a users builder object with jdbcAuthentication, 
that will compare the user and password values sent by each user from the login page with 
the data (encrypted or not) that resides in the database (through the SecurityDataSource).
A SecurityDataSource bean is injected, after being created and have its dependencies injected 
as defined at MyApplicationContextConfig class. This SecurityDataSource is passed as argument 
to the dataSource() method of the AuthenticationManagerBuilder auth object, so that all 
authentication now is done based on database and not more on memory stored user data as in the 
last project (https://github.com/danielpm1982/MAVEN/tree/master/Maven_Web_Spring_JPA_AOP_NoXML_Security_Default).

This class also overrides the configure(HttpSecurity http) method to demand that any http request be 
authenticated, as well as to set a custom login page, and permit all users to see the login form, 
as well as be able to logout, ending the session. The url for the loginPage must match the MySecurityController 
getMapping url. The url for the loginProcessingUrl must match the loginPage from action url. 
The POST request arguments of the loginPage are treated automatically and compared to the previously registered 
users' data (at the database), for authenticating or not each user (Session scoped). This method also, instead of 
authorizing requests from any authenticated user, defines, for each request mapping path, the roles that are 
authorized to access it after authentication. At the securityController, each path is mapped to a jsp page, whose 
authorization is denied for non authenticated users as well as for non-authorized user roles according to this method 
antMatchers declarations.

This method configures, thus, not only the authentication based on the database, but also the authorization 
of each role for accessing each mapping path.

Definition of an exception handling 'access denied' page is done at the end, pointing out to a url that
will serve as a request mapping path for the custom error handling page, instead of the ugly
default one.

Regarding the encryption or not, it is basically done at the database itself through the {id} that precedes the
password value. If the id is 'noop' (no operation), then no encryption is considered and the value is retrieved
as it is. If the id is 'bcrypt' (Bcrypt encryption), on the other hand, then the framework will know that that
value is encrypted with bcrypt algorithm. As it is a one-way encryption and the value can't be decrypted due to 
the random salts, Spring will first automatically and internally bcrypt the login password the user is sending 
during his authentication attempt and then compare that to the value that it has retrieved from the database, 
to see if they're compatible. If so, the original password values must be the same and authentication is concluded, 
and authorizations for each mapping path begins. If not, the user is rejected.
*/
