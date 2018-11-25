package com.danielpm1982.Maven_Web_Spring_NoXML_REST_WebServices.controller;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.danielpm1982.Maven_Web_Spring_NoXML_REST_WebServices.entity.Person;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Controller
@RequestMapping("/controller")
public class MyRestWebServiceLocalFileController {
	@RequestMapping("/showRestWebServiceUserInterface")
	public String showRestWebServiceUserInterface() {
		return "webServiceUI";
	}
	@RequestMapping("/showRestWebServiceUserInterfaceResult")
	public String showRestWebServiceUserInterfaceResult(Model model, HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		Path pathBase = Paths.get("/home/daniel/eclipse-workspace/Maven_Web_Spring_NoXML_REST_WebServices/data");
		
		//reading from .json file to POJO
		Person person = mapper.readValue(Paths.get(pathBase.toString(),"/person.json").toUri().toURL(), Person.class);
		model.addAttribute("person", person);
		
		//writing from POJO to another .json file
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.writeValue(Paths.get(pathBase.toString(),"/person2.json").toFile(), person);
		
		model.addAttribute("person", person);
		return "webServiceUIResult";
	}
}

