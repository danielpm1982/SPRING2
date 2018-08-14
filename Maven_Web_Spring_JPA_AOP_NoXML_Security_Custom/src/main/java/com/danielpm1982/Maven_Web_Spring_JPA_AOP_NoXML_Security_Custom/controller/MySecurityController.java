package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MySecurityController {
	@GetMapping("/customLoginPage")
	public String showCustomLoginPage() {
		return "login";
	}
}

/*
This Controller class mapps the requests to the jsp login page, based on the custom login 
url defined at the security configuration class (MyWebSecurityConfigurerAdapter). The login
page then, after the user types the login arguments, returns these to the processing url, also 
defined at the security config class... and the framework does the comparisons to the previously 
registered user data in order authenticate or not that specific user. If not authenticated,
Spring turns back to the login page again, and appends an 'error' request param at the page url,
which can be used to display or not error messages programatically. The error messages here
are not handled automatically as with default login pages.
*/
