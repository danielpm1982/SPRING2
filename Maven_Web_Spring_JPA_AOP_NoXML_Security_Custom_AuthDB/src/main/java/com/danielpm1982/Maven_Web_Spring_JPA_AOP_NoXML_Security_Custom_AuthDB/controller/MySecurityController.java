package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("controller")
@Controller
public class MySecurityController {
	@GetMapping("/customLoginPage")
	public String showCustomLoginPage() {
		return "login";
	}
	@GetMapping("/administration")
	public String showAdministrationPage() {
		return "administration";
	}
	@GetMapping("/directory")
	public String showDirectoryPage() {
		return "directory";
	}
	@GetMapping("/ceo")
	public String showCEOPage() {
		return "ceo";
	}
	@GetMapping("/accessDenied")
	public String showAccessDeniedPage() {
		return "accessDenied";
	}
}

/*
This Controller class maps the requests to the jsp login page, based on the custom login 
url defined at the security configuration class (MyWebSecurityConfigurerAdapter). The login
page then, after the user types the login arguments, returns these to the processing url, also 
defined at the security config class... and the framework does the comparisons to the previously 
registered user data in order authenticate or not that specific user. If not authenticated,
Spring turns back to the login page again, and appends an 'error' request param at the page url,
which can be used to display or not error messages programatically. The error messages here
are not handled automatically as with default login pages. In a similar way, when the user
logs out, Spring redirects to the main login page and appends a 'logout' request parameter at the
page url, which can be used to display a logged out confirmation message. For handling csrf security, 
different approaches are used (see login.jsp). Spring Security taglibs are used to get user info,
as username and roles - authorities (see form.jsp).
All other request mapping paths here, other than login, has the authorization conditioned not
only to the authentication of the user but also to the authorization (or not) for each role, 
as defined at the MyWebSecurityConfigurerAdapter class. When trying to access each context path,
if the authenticated user is authorized, than he is directed to the jsp page. If not, a forbidden
message appears.
The accessDenied url defined at the MyWebSecurityConfigurerAdapter class is mapped to the custom 
error handling jsp page that will substitute the default one.
*/
