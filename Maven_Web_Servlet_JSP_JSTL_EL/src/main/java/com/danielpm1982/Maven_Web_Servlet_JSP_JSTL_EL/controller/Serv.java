package com.danielpm1982.Maven_Web_Servlet_JSP_JSTL_EL.controller;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Serv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

/*
This is a sample template for Maven Web Apps with a POM containing the dependencies needed
for the servlet-api core, jsp, expression language (EL) and jstl.
For avoiding compiling and execution errors, whenever Maven->updateProject is called, it must
also be called the project->clean menu command at Eclipse, so that the target classes and
resources are again built. Maven->updateProject updates the local repository with the new 
dependencies but deletes the target directory contents. A Server (Tomcat) cleaning is also 
recomended if any backend file alteration is done.
For java source files, the default directory path is src->main->java. This is where the compiler
will look for any package with any classes, including servlets, in this example. At the web.xml,
the full-qualified name of the servlet class should be set starting after this default path
(com.danielpm1982.mavenWeb.controller.Serv in this case).
For web files, the default path is src->main->webapp (src->main->webapp->welcome.jsp, for ex).
This template example simply has a welcome jsp that sends the flux to a servlet, which sets
a message as a request attribute and sends the flux back to a result jsp, which is seen by the user.
*/
