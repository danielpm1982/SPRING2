package com.danielpm1982.Maven_Web_Spring_NoXML_REST_WS.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.danielpm1982.Maven_Web_Spring_NoXML_REST_WS.entity.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/controller")
public class MyRestWebServiceRemoteController {
	@RequestMapping("/showRestWebServiceRemoteUserInterfaceResult")
	public String showRestWebServiceRemoteUserInterfaceResult(Model model) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		//reading from remote .json file to POJO
		MappingIterator<User> userMap = mapper.readerFor(User.class).readValues(new URL("https://jsonplaceholder.typicode.com/users"));
		List<User> userList = new ArrayList<>(); 
		userMap.forEachRemaining(x->userList.add(x));
		model.addAttribute("userList", userList);
		return "webServiceRemoteUIResult";
	}
}

/*
This class reads a .json from a remote Rest Web Service and puts the multiple User instances
contained at the file in a Map, which is transformed to a List of Users and returned to the view.
*/