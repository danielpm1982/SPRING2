package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyWelcomeController {
	@GetMapping("/")
	public String showForm() {
		return "redirect:controller/form";
	}
}

/*
This controller simply maps the "/" requests to the controller/form url. 
*/
