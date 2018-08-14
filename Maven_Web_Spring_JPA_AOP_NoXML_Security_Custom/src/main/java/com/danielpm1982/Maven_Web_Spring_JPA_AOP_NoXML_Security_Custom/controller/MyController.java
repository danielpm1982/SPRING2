package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom.controller;
import javax.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom.entity.EntityModelBank;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom.entity.EntityModelClass;

@Controller
@RequestMapping("/controller")
public class MyController {
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
two dependencies for the Security are also added (spring-security-web and spring-security-config). 
Separation of concerns is clearly obeyed as there's no modification on the rest of the code for substituting .xml files with 
java config ones, or for adding the Security layer based on Servlet Filters. See the config package of
this project for more comments.

PS.: JPA is merely simulated here with Collections (entity package), until no-XML confs for the 
DataSource, SessionFactory and Transaction-Manager are implemented at the MyApplicationContextConfig.java 
class.

*/
