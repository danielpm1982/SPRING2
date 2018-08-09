package com.danielpm1982.Maven_Web_Spring_JPA_AOP.controller;
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
import com.danielpm1982.Maven_Web_Spring_JPA_AOP.entity.EntityModelClass;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP.service.PersistenceServiceInterface;

@Controller
@RequestMapping("/controller")
public class MyController {
	@Autowired
	@Qualifier("persistenceService")
	private PersistenceServiceInterface persistenceServiceInterface;
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
		boolean inserted = persistenceServiceInterface.insertEntity(entityModelClass);
		model.addAttribute("inserted", inserted);
		model.addAttribute("entity", entityModelClass);
		model.addAttribute("entityList", persistenceServiceInterface.searchAllEntities());
		return "formResult";
	}
	@InitBinder
	public void initBinder(WebDataBinder wdb) {
		StringTrimmerEditor trimmer = new StringTrimmerEditor(true);
		wdb.registerCustomEditor(String.class, trimmer);
	}
}


/*
--> This is a template example for using Maven together with SpringMVC, JPA and AOP.
--> All POM dependencies are set at pom.xml .
--> Remember to use mysql5 as the dialect and hibernate.hbdll2.auto as a prop key at applicationContext.xml 
sessionFactory bean hibernateProperties for the hibernate session to be created correctly.
--> Do a Project Clean (rebuild) after each time Maven/UpdateProject is called.
--> The functionality of this project is basically of creating an EntityModelClass bean that is passed to the 
Spring form as the modelAttribute to have its properties set (bound). Actually, only one property - 
'entityDescription' - is set by the user (validated against empty values), the other two are set automatically: 
the 'id' by Hibernate when the bean object is saved, and the other - 'dateTime' - at the bean Constructor.
--> This project can evolve with new DAO features, new Aspect advices and pointCuts, new custom or customized 
Spring validation rules for new Entities, as well as with the addition of Spring Security and RESTful services. 
This project intends to be only a basic template for that.  
*/
