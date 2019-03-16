package com.danielpm1982.vaccinesRecord.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.danielpm1982.vaccinesRecord.service.UserServiceInterface;

@Configuration
@EnableWebSecurity
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	@Autowired
    @Qualifier("userService")
    private UserServiceInterface userService;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(authenticationProvider());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").permitAll() //no personal data
		.antMatchers("/security/**").permitAll() //no personal data
		.antMatchers("/tps/").permitAll() //individual personal data exposed if logged in
		.antMatchers("/tps/vaccinesResources/**").hasRole("USER") //no personal data
		.antMatchers("/tps/vaccinesByPatientId/**").hasAnyRole("PATIENT") //individual personal data exposed
		.antMatchers("/mis/**").hasAnyRole("MANAGER", "DIRECTOR") //multiple personal data exposed
		.antMatchers("/api/**").hasAnyRole("MANAGER", "DIRECTOR") //multiple personal data exposed
		.antMatchers("/ws/**").hasAnyRole("MANAGER", "DIRECTOR") //multiple personal data exposed
		.and()
		.formLogin().loginPage("/security/login").loginProcessingUrl("/security/loginResult")
		.successHandler(myAuthenticationSuccessHandler).permitAll().and()
		.logout().logoutSuccessHandler(myLogoutSuccessHandler).permitAll().and()
		.exceptionHandling().accessDeniedPage("/security/accessDenied");
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(15);
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
}
