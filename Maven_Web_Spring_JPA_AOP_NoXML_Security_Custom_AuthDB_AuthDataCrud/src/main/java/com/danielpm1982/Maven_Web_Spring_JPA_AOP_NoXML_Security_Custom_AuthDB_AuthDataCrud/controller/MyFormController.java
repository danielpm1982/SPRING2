package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.controller;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.entity.EntityModel;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.service.EntityModelService;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.service.UserServiceInterface;

@Controller
@RequestMapping("/controller")
public class MyFormController {
	@Autowired
	EntityModelService entityModelService;
	@Autowired
	@Qualifier("userService")
	private UserServiceInterface userService;
	@RequestMapping("/form")
	public String showForm(Principal principal, HttpServletRequest request, Model model) {
		model.addAttribute("entityModel", new EntityModel());
		request.getSession().setAttribute("user",userService.findByUserName(principal.getName()));
		return "form";
	}
	@RequestMapping("/formResult")
	public String showFormResult(@Valid @ModelAttribute("entityModel") EntityModel entityModel, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			model.addAttribute("entityModel", entityModel);
			return "form";
		}
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
 
This is a project similar to https://github.com/danielpm1982/MAVEN/tree/master/Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB,
with no XML files - the whole configuration is done through java configuration classes... but now with a complete 
CRUD regarding the entities related to the authentication and authorization security processes, which are done here 
using Service/DAO/Entity of auth objects (JPA/Hibernate) instead of jdbc automatic and internal management of authentication/authorization, 
as in the previous project.

Persistence with Hibernate is fully functional using DataSource, SessionFactory and Transaction-Manager 
at the MyApplicationContextConfig.java class, both for the business as well as to the auth entities.
The SessionFactory is injected into the DAO classes for getting the sessions, both for business as for
auth persistence management methods.

For the password data only {bcrypt} encrypted values are used, this time.

For more info on configuration details see the config commented classes.

This Controller class, basically deals with the mapping url and persistence regarding the business entity.

For auth entities, see the MyRegistrationController.java Controller class.
*/
