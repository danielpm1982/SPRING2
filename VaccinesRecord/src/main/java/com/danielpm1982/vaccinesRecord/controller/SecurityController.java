package com.danielpm1982.vaccinesRecord.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/security")
public class SecurityController {
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@RequestMapping("/loginResult")
	public String loginResult() {
		return "redirect:/tps";
	}
	@RequestMapping("/logout")
	public String logout() {
		return "redirect:/security/login?logout=true";
	}
	@RequestMapping("/accessDenied")
	public String showAccessDeniedPage() {
		return "accessDenied";
	}
}
