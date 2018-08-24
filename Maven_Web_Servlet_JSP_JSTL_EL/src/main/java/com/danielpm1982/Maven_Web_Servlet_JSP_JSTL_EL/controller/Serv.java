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
--> Unfortunately, at Eclipse Photon, at least, if the web.xml file has an old header, each time a Maven Update 
is done, Eclipse simply removes Java Build Path Entries, including Maven Dependencies, from the Deployment Assembly, 
resulting in a Classpath Dependency Validator Message telling that 
'Classpath entry org.eclipse.m2e.MAVEN2_CLASSPATH_CONTAINER will not be exported or published. Runtime 
ClassNotFoundExceptions may result." And, without Maven Dependencies, the project doesn't run. So, after each Maven Update, 
check if the error occurs, and, if it does, add the Java Build Path Entries manually at the Deployment Assembly. Or simply
update the web.xml for a project facet Dynamic Web Module 4.0 compatible header.
--> also take care with the .xml headers, for avoiding project facets incompatibilities and the xml not being recognized 
(.xml missing errors).
--> if a Project Facets Dynamic Web Module change is needed, and if the change is blocked at the IDE (generally beacuse
of an old web.xml header), the change can be done at the .xml file (or simply update the header): 
eclipse-workspace/ProjectDirectory/.settings/org.eclipse.wst.common.project.facet.core.xml . 
--> with the new JDKs, JAXB jars have been turned unavailable or not added to the buildPath of the projects
by default. Better than adding modules through VM arguments, which will also be a deprecated solution with JAVA 11, 
a final solution for all JAVA versions is to simply add the dependencies for JAXB at the POM file, whose artifactId are: 
<artifactId>jaxb-api</artifactId>
<artifactId>jaxb-core</artifactId>
<artifactId>jaxb-impl</artifactId>
<artifactId>activation</artifactId> .
This solves the error for classNotFound regarding JAXB classes.
--> some other errors, due to missing or unregistered jars, can be solved by setting the Apache Tomcat as a targeted
runtime. And choosing the correct projects facets, for instance, by choosing java 10 when using java 10 jdk or jre.
*/
