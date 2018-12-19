package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.service.UserServiceInterface;

@Configuration
@EnableWebSecurity
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	@Autowired
    @Qualifier("userService")
    private UserServiceInterface userService;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/").hasRole("USER")
//		.antMatchers(HttpMethod.GET, "/controller/api/**").hasRole("USER")
		.antMatchers("/controller/administration/**").hasAnyRole("ADMIN", "MANAGER")
		.antMatchers("/controller/directory/**").hasRole("DIRECTOR")
		.antMatchers("/controller/ceo/**").hasRole("CEO").and()
		.formLogin().loginPage("/controller/customLoginPage").loginProcessingUrl("/controller/customLoginPageResult")
		.successHandler(myAuthenticationSuccessHandler).permitAll().and()
		.logout().permitAll().and()
		.exceptionHandling().accessDeniedPage("/controller/accessDenied");
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
}

/*
This config class enables and configurates WebSecurity.

Authentication is set by overriding the configure(AuthenticationManagerBuilder auth) method 
of WebSecurityConfigurerAdapter, and passing to the authenticationProvider method a bean
as argument, which itself will have the userService and passwordEncoder beans encapsulated,
so that the auth object will be able to encrypt the data and use the JPA sessionFactory of 
the Service object to access the DBMS, for committing and retrieving authentication information. 

There's no more reason to use the securityDataSource bean injected directly here, from 
MyApplicationContextConfig class. This SecurityDataSource is implicitly used and already injected
at the DAO object inside the Service object, which is here passed to the authenticationProvider, 
and this is the only one to be passed to the auth object. Differently from last projects
(https://github.com/danielpm1982/MAVEN/tree/master/Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB, etc),
no inMemory authentication is used nor jdbc explicitly (with the dataSource bean), but a Service that 
has a DAO, which access the DBMS through a JPA injected sessionFactory. And at the Service and at the DAO you have 
the CRUD methods for manipulating the entities related to authentication (User and Role). 

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

This method configures, thus, not only the authentication based on the database auth data, but also the authorization 
of each user role for accessing each mapping path.

Definition of an exception handling 'access denied' page is done at the end, pointing out to a url that
will serve as a request mapping path for the custom error handling page, instead of the ugly
default one.

The encryption is done through the Bcrypt internal algorithm. Old values at the database could be 'noop' (no operation), 
then no encryption is considered and the value is retrieved as it is. But with ALL values persisted from this app, the id 
is 'bcrypt' (Bcrypt encryption), and the framework will know that that value is encrypted with bcrypt algorithm. 
As it is a one-way encryption and the value can't be decrypted due to 
the random salts, Spring will first automatically and internally bcrypt the login password the user is sending 
during his authentication attempt and then compare that to the value that it has retrieved from the database, 
to see if they're compatible. If so, the original password values must be the same and authentication is concluded, 
and authorizations for each mapping path begins. If not, the user is rejected.

http.csrf().disable() has been added for allowing external requests from PostMan (GET, POST, PUT, DELETE). Cross-site request 
forgery protection blocked all method requests except GET ones.

Regarding the REST api, it should have been implemented the security authentication/authorization for all their endpoint urls, 
as in ".antMatchers(HttpMethod.GET, "/controller/api/**").hasRole("USER")". But, for testing with PostMan, it would demand
token acquiring for this to be used at each REST api request, and, for simplicity, the REST api endpoints were left without 
security. All other request mapping urls of the app are secured, except the ones in the RestController. 

*/
