package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		@SuppressWarnings("deprecation")
		UserBuilder users = User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication()
			.withUser(users.username("user1").password("123").roles("user"))
			.withUser(users.username("user2").password("123").roles("manager", "admin", "user"))
			.withUser(users.username("user3").password("123").roles("director", "manager", "admin", "user"))
			.withUser(users.username("user4").password("123").roles("CEO", "director", "manager", "admin", "user"));
	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().anyRequest().authenticated().and()
//		.formLogin().loginPage("/customLoginPage").loginProcessingUrl("/customLoginPageResult").permitAll().and()
//		.logout().permitAll();
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").hasRole("user")
		.antMatchers("/controller/administration/**").hasAnyRole("admin", "manager")
		.antMatchers("/controller/directory/**").hasRole("director")
		.antMatchers("/controller/ceo/**").hasRole("CEO").and()
		.formLogin().loginPage("/controller/customLoginPage").loginProcessingUrl("/controller/customLoginPageResult").permitAll().and()
		.logout().permitAll().and()
		.exceptionHandling().accessDeniedPage("/controller/accessDenied");
	}
}

/*
This config class enables and configurates WebSecurity, in this case overriding the
configure(AuthenticationManagerBuilder auth) method of WebSecurityConfigurerAdapter,
creating a users builder object with defaultPasswordEncoder (plain-text, not encrypted),
setting the auth builder with inMemoryAuthentication, and adding each user along with
their username, password and roles through the users builder object, as arguments. 
This class also overrides the configure(HttpSecurity http) method to demand that any
http request be authenticated, as well as to define a custom login page, and permiting all
users to see the login form. And also be able to logout, ending the session.
The url for the loginPage must match the MySecurityController getMapping url.
The url for the loginProcessingUrl must match the loginPage action url. 
The POST request arguments of the loginPage are treated automatically and compared to the 
previously registered users', for authenticating or not each user, and then for authorizing
all authenticated users requests for resources thereafter (Session scoped).
The second configure(HttpSecurity http), instead of authorizing requests from any 
authenticated user, defines, for each request mapping path, the roles that are
authorized to access it after authentication. At the controller, each path will be
mapped to a jsp page, whose authorization will be denied for non authenticated users AND 
for non authorized user roles as defined in this method.
This method configures, thus, not only the authentication, but also the authorization for 
each role.
Defining exception handling access denied page is done at the end, pointing to a url that
will serve as a request mapping path for the custom error handling page, instead of the ugly
default one. 
*/
