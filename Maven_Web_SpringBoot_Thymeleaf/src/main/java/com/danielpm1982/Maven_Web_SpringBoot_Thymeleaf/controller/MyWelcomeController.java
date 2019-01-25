package com.danielpm1982.Maven_Web_SpringBoot_Thymeleaf.controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyWelcomeController {
	@GetMapping("/")
	public String hello(Model model) {
		String localDateTime = getLocalDateTimeString();
		model.addAttribute("localDateTime", localDateTime);
		return "welcome";
	}
	private String getLocalDateTimeString() {
		return LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
	}
}
