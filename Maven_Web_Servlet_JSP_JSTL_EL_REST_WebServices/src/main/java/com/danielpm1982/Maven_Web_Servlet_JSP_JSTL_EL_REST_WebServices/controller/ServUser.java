package com.danielpm1982.Maven_Web_Servlet_JSP_JSTL_EL_REST_WebServices.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.danielpm1982.Maven_Web_Servlet_JSP_JSTL_EL_REST_WebServices.entity.User;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		//reading from .json file to POJO
		MappingIterator<User> userMap = mapper.readerFor(User.class).readValues(new URL("https://jsonplaceholder.typicode.com/users"));
		List<User> userList = new ArrayList<>(); 
		userMap.forEachRemaining(x->userList.add(x));
		request.setAttribute("userList", userList);
		request.setAttribute("result", "OK! MVC sequence successfull !");
		RequestDispatcher rd = request.getRequestDispatcher("resultUser.jsp");
		rd.forward(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
}
