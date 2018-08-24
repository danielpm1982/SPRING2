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
--> Unfortunately, at Eclipse Photon, at least, each time a Maven Update is done, Eclipse simply removes 
Java Build Path Entries, including Maven Dependencies, from the Deployment Assembly, resulting in a Classpath 
Dependency Validator Message telling that 'Classpath entry org.eclipse.m2e.MAVEN2_CLASSPATH_CONTAINER will not 
be exported or published. Runtime ClassNotFoundExceptions may result." And, without Maven Dependencies, the 
project doesn't run. So, after each Maven Update, check if the error occurs, and, if it does, add the Java Build 
Path Entries again at the Deployment Assembly.
--> if a Project Facets Dynamic Web Module change is needed, and if the change is blocked at the IDE, 
the change can be done at the .xml file: 
eclipse-workspace/ProjectDirectory/.settings/org.eclipse.wst.common.project.facet.core.xml . 
--> with the new JDKs, JAXB jars have been turned unavailable or not added to the buildPath of the projects
by default. Better than adding modules through VM arguments, which will also be a deprecated solution with JAVA 11, 
a final solution for all JAVA versions is to simply add the dependencies for JAXB at the POM file, whose artifactId are: 
<artifactId>jaxb-api</artifactId>
<artifactId>jaxb-core</artifactId>
<artifactId>jaxb-impl</artifactId>
<artifactId>activation</artifactId> .
This solves the error for classNotFound regarding JAXB classes.
--> also take care with the .xml headers, for avoiding project facets incompatibilities and the xml not being recognized 
(.xml missing errors). 
--> some other errors, due to missing or unregistered jars, can be solved by setting the Apache Tomcat as a targeted
runtime. And choosing the correct projects facets, for instance, by choosing java 10 when using java 10 jdk or jre.
*/
