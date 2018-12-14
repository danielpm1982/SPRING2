package com.danielpm1982.Maven_Web_Spring_NoXML_REST_WebServices.controller;
import java.io.IOException;
import java.net.URISyntaxException;
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
	public String showRestWebServiceUserInterfaceResult(Model model, HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		ObjectMapper mapper = new ObjectMapper();
		
		String basePathName1 = "/home/daniel/eclipse-workspace/Maven_Web_Spring_NoXML_REST_WebServices/data/";
		String basePathName2 = "/home/daniel/eclipse-workspace/Maven_Web_Spring_NoXML_REST_WebServices/target/classes/com/danielpm1982/Maven_Web_Spring_NoXML_REST_WebServices/resources/data/";
		String fileToRead = "person.json";
		String fileToWrite = "person2.json";
		
		//reading from .json file to POJO
//		Person person = mapper.readValue(Paths.get(basePathName1, fileToRead).toFile(), Person.class);
		Person person = mapper.readValue(Paths.get(basePathName2, fileToRead).toFile(), Person.class);
		
		model.addAttribute("person", person);
		//writing from POJO to another .json file
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
//		mapper.writeValue(Paths.get(basePathName1, fileToWrite).toFile(), person);
		mapper.writeValue(Paths.get(basePathName2, fileToWrite).toFile(), person);
		return "webServiceUIResult";
	}
}

/*
This class simply reads a local .jon file, from no Web Service, transforms it to a POJO and 
sends this instance to the view.  
Additionally, it also creates another .json file from the POJO data, and saves to the same
directory of the first one.  
*/

/*
Two basePaths have been used here, one pointing to a fileSystem path, a "data" folder right under the project folder, and another,
at the target folder, generated after the project is compiled. 
Both for reading and writing the .json files.
Instead of relative, absolute paths have been used. Relative ones could also be used, by calling getClass.getResource() or getClass.getResourceAstream().
*/
