package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Default.config;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyAnnotationConfigDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	public MyAnnotationConfigDispatcherServletInitializer() {
	}
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{ MyApplicationContextConfig.class };
	}
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}

/*
 This config class initializes the dispatcher servlet, pointing to the config applicationContext class.
it is analogous to web.xml. Extends AbstractAnnotationConfigDispatcherServletInitializer. And overrides 
the three methods above.
*/  