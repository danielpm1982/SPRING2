package com.danielpm1982.Maven_Web_Spring_NoXML_REST_WebServices.controller;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@RequestMapping("/controller")
public class MyWebServiceRESTController {
	@RequestMapping("/getLocalRESTWebServiceResult")
	public String getLocalRESTWebServiceResult() throws JsonParseException, JsonMappingException, IOException {
		String resultMessage = "The REST webService responded: Hello World !";
		return resultMessage;
	}
}
