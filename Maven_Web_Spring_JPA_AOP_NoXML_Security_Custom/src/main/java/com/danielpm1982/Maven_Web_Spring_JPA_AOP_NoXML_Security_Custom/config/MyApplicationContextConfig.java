package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages="com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom")
@EnableWebMvc
@EnableAspectJAutoProxy
public class MyApplicationContextConfig implements WebMvcConfigurer{
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}

/*
This is a Configuration class that substitutes the ApplicationContext.xml :
- the <context:component-scan base-package=""></context:component-scan> is substituted for the
annotation @ComponentScan(basePackages="");
- the <mvc:annotation-driven /> is substituted for the annotation @EnableWebMvc;
- the <aop:aspectj-autoproxy /> is substituted for the annotation @EnableAspectJAutoProxy;
- the 
	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/view/" />
		<property name="suffix" value=".jsp" />
  	</bean>
  is substituted for the ViewResolver viewresolver() bean generator method, which instantiates
  an InternalResourceViewResolver object and sets the prefix and sufix, returning this object as
  the bean;
- the <mvc:resources mapping="/resources/**" location="/resources/"></mvc:resources> is substituted
for the addResourceHandlers method, overriden from the WebMvcConfigurer interface that this class 
must implement (for registering this resource handler). 
*/
