package com.danielpm1982.Maven_Web_SpringBoot_REST_WS.controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyWelcomeRESTController {
	@Value("${welcomeMessage}")
	private String welcomeMessage;
	@GetMapping("/")
	public String initiate() {
		return welcomeMessage+" "+LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
	}
}
