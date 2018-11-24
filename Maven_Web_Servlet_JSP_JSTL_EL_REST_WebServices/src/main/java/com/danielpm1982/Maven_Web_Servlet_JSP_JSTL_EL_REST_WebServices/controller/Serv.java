package com.danielpm1982.Maven_Web_Servlet_JSP_JSTL_EL_REST_WebServices.controller;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.danielpm1982.Maven_Web_Servlet_JSP_JSTL_EL_REST_WebServices.entity.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Serv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Path path = Paths.get("/home/daniel/eclipse-workspace/Maven_Web_Servlet_JSP_JSTL_EL_REST_WebServices/data");
		
		//reading from .json file to POJO
		Person person = mapper.readValue(Paths.get(path.toString(),"/person.json").toUri().toURL(), Person.class);
		
		//writing from POJO to another .json file
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.writeValue(Paths.get(path.toString(),"/person2.json").toFile(), person);
		
		request.setAttribute("person", person);
		request.setAttribute("result", "OK! MVC sequence successfull !");
		RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
		rd.forward(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
}
