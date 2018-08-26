package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB.controller;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB.entity.EntityModel;
//import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB.entity.EntityModelBank;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB.service.EntityModelService;

@Controller
@RequestMapping("/controller")
public class MyFormController {
	@Autowired
	EntityModelService entityModelService;
	@RequestMapping("/form")
	public String showForm(Model model) {
		model.addAttribute("entityModel", new EntityModel());
		return "form";
	}
	@RequestMapping("/formResult")
	public String showFormResult(@Valid @ModelAttribute("entityModel") EntityModel entityModel, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			model.addAttribute("entityModel", entityModel);
			return "form";
		}
//		boolean inserted = EntityModelBank.addEntityModelClassToBank(entityModelClass);
		boolean inserted = entityModelService.add(entityModel);
		model.addAttribute("inserted", inserted);
		model.addAttribute("entity", entityModel);
		model.addAttribute("entityList", entityModelService.findAll());
		return "formResult";
	}
	@InitBinder
	public void initBinder(WebDataBinder wdb) {
		StringTrimmerEditor trimmer = new StringTrimmerEditor(true);
		wdb.registerCustomEditor(String.class, trimmer);
	}
}


/*
Before running this app, along with a MySQL DBMS, please first create the Database running the script: 
SQLInitialSchemeAndData/InitialSchemeCreation.sql . It creates the scheme, the tables and populates the
tables with initial values.
 
This is the same project of https://github.com/danielpm1982/MAVEN/tree/master/Maven_Web_Spring_JPA_AOP,
added with the war-plugin dependencies (for not showing no-web.xml errors), two classes that substitute
the web.xml (MyAnnotationConfigDispatcherServletInitializer) and applicationContext.xml 
(MyApplicationContextConfig), and two other classes that initializes the Spring Security Filters
(MySecurityWebApplicationInitializer) and configurates a custom login form 
(MyWebSecurityConfigurerAdapter), based on the authentication and users set at this class. At the POM, 
three dependencies for the Security are also added (spring-security-web, spring-security-config and 
spring-security-taglibs). Separation of concerns is clearly obeyed as there's no modification on the rest 
of the code for substituting .xml files with java config ones, or for adding the Security layer based on 
Servlet Filters. See the config package of this project for more comments.

PS.: JPA initially was merely simulated here with Collections (entity package), until no-XML confs for the 
DataSource, SessionFactory and Transaction-Manager were implemented at the MyApplicationContextConfig.java 
class. Now Persistence with Hibernate it's fully functional.

In this project, comparing with the previous one 
(https://github.com/danielpm1982/MAVEN/tree/master/Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom), the 
difference is the storage and retrieving of registered user data from databases, using Spring inbuilt jdbc, 
instead of in-memory storage of explicitly hard-coded user data (username, password, roles). 
Dependencies at POM - mysql-connector-java and c3p0 - are added (uncommented). Default Spring security tables 
are created and populated with users' data (see SQLInitialSchemeAndData/InitialSchemeCreation.sql). A properties file is created 
with all config values for the jbdc and the connection pool, to be used at the config classes. SecurityDataSource 
is configured at MyApplicationContextConfig class. A SecurityDataSource bean instance (already injected)
is used as argument at the MyWebSecurityConfigurerAdapter configure(AuthenticationManagerBuilder auth) method, 
while auth object also enables jdbc authentication, instead of a in-memory one. No other main changes are done 
elsewhere in the code. See config classes for more.

For the password data two id-types are inserted at the DB: {noop}, for plain-text, and {bcrypt}, for bcrypted values.
The most advisable is of course to encrypt the passwords, for instance, with bcrypt algorithm.

A manual bcrypt service can be found at: https://www.dailycred.com/article/bcrypt-calculator. The original
password values (123) have been bcrypted at this page algorithm before being set directly at the database. See
the /Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB/src/main/SQLInitialSchemeAndData/DBCreationScript.sql 
for the final values. {noop} and {bcrypt} ids have been used, as a way of demonstrating plain-text and encrypted
password values at the database. In a real application, only encrypted values should be used.
*/
