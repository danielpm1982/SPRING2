package com.danielpm1982.vaccinesRecord.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
	@Value("${welcomeMessage}")
	private String welcomeMessage;
	@Value("${credits}")
	private String credits;
	@Value("${email}")
	private String email;
	@RequestMapping("/")
	public String hello(Model model) {
		model.addAttribute("welcomeMessage", welcomeMessage);
		model.addAttribute("credits", credits);
		model.addAttribute("email", email);
		return "welcome";
	}
	@GetMapping("/error")
	public String showError(Model model) {
		return "error";
	}
}
