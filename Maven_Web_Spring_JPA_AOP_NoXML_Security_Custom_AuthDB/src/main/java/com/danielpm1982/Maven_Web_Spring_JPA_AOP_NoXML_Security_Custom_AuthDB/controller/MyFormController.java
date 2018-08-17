package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB.controller;
import javax.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB.entity.EntityModelBank;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB.entity.EntityModelClass;

@Controller
@RequestMapping("/controller")
public class MyFormController {
	@RequestMapping("/form")
	public String showForm(Model model) {
		model.addAttribute("entityModelClass", new EntityModelClass());
		return "form";
	}
	@RequestMapping("/formResult")
	public String showFormResult(@Valid @ModelAttribute("entityModelClass") EntityModelClass entityModelClass, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			model.addAttribute("entityModelClass", entityModelClass);
			return "form";
		}
		boolean inserted = EntityModelBank.addEntityModelClassToBank(entityModelClass);
		model.addAttribute("inserted", inserted);
		model.addAttribute("entity", entityModelClass);
		model.addAttribute("entityList", EntityModelBank.getBank());
		return "formResult";
	}
	@InitBinder
	public void initBinder(WebDataBinder wdb) {
		StringTrimmerEditor trimmer = new StringTrimmerEditor(true);
		wdb.registerCustomEditor(String.class, trimmer);
	}
}


/*
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

PS.: JPA is merely simulated here with Collections (entity package), until no-XML confs for the 
DataSource, SessionFactory and Transaction-Manager are implemented at the MyApplicationContextConfig.java 
class.

In this project, comparing with the previous one 
(https://github.com/danielpm1982/MAVEN/tree/master/Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom), the 
difference is the storage and retrieving of registered user data from databases, using Spring inbuilt jdbc, 
instead of in-memory storage of explicitly hard-coded user data (username, password, roles). 
Dependencies at POM - mysql-connector-java and c3p0 - are added (uncommented). Default Spring security tables 
are created and populated with users' data (see resources/DBCreationScript.sql). A properties file is created 
with all config values for the jbdc and the connection pool, to be used at the config classes. SecurityDataSource 
is configured at MyApplicationContextConfig class. A SecurityDataSource bean reference (already injected)
is used as argument at the MyWebSecurityConfigurerAdapter configure(AuthenticationManagerBuilder auth) method, 
while auth object also enables jdbc authentication, instead of a in-memory one. No other main changes are done 
elsewhere in the code. See config classes for more.

*/