package com.danielpm1982.Maven_Web_Spring_NoXML_REST_WebServices.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyWelcomeController {
	@GetMapping("/")
	public String initiate() {
		return "redirect:controller/showRestWebServiceUserInterface";
	}
}

/*
There are different things being exemplified in this project:

1) Through the local file controller, a local .json file is read and mapped to a Person POJO
in order to be displayed at the respective view. It is also tested the writing of the POJO data
to another .json file, created at runtime, at the same data directory.  

2) Through the remote controller, a remote webService is accessed, retrieving a .json file
containing multiple User object data, which is transfered to a Map, and then to a List of Users,
and later displayed the list at the respective view.

3) Using a self WebService @RestController, clients can request .json files from the RestController
using http requests. At the RestController, both the http request as well as the controller response
can be transformed to the respective desired formats automatically: POJO and .json file.

*/
