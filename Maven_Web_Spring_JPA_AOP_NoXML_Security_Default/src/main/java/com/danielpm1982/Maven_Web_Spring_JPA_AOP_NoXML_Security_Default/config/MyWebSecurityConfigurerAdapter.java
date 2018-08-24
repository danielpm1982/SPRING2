package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Default.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
			.withUser(users.username("user1").password("123").roles("ROLE1"))
			.withUser(users.username("user2").password("123").roles("ROLE2"))
			.withUser(users.username("user3").password("123").roles("ROLE3"));
	}
}

/*
This config class enables and configurates WebSecurity, in this case overriding the
configure(AuthenticationManagerBuilder auth) method of WebSecurityConfigurerAdapter,
creating a users builder object with defaultPasswordEncoder (plain-text, not encrypted),
setting the auth builder with inMemoryAuthentication, and adding each user along with
their username, password and roles through the users builder object, as arguments. A
default login form, based on html, is created by the framework, as no custom login form
has been defined here.
*/
