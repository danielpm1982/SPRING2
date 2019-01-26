package com.danielpm1982.Maven_Web_SpringBoot_JSP.controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyWelcomeController {
	@Value("${welcomeMessage}")
	private String welcomeMessage;
	@Value("${localDateTimeMessage}")
	private String localDateTimeMessage;
	@GetMapping("/")
	public String hello(Model model) {
		String localDateTime = getLocalDateTimeString();
		model.addAttribute("welcomeMessage", welcomeMessage);
		model.addAttribute("localDateTimeMessage", localDateTimeMessage);
		model.addAttribute("localDateTime", localDateTime);
		return "welcome";
	}
	private String getLocalDateTimeString() {
		return LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
	}
}
