package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB.config;
import java.beans.PropertyVetoException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@ComponentScan(basePackages="com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB")
@EnableWebMvc
@EnableAspectJAutoProxy
@PropertySource("classpath:persistence-mysql.properties")
public class MyApplicationContextConfig implements WebMvcConfigurer{
	@Autowired
	private Environment env;
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
	@Bean
	public DataSource securityDataSource() {
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		return securityDataSource;
	}
	private int getIntProperty(String propertyString) {
		return Integer.parseInt(env.getProperty(propertyString));
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

/*
This class also creates the SecurityDataSource bean, for later use at MyWebSecurityConfigurerAdapter class,
in order to store and retrieve registered user data to/from databases (encrypted or not), instead of to/from 
program memory.
As commented at MyFormController class, additional POM dependencies have been set; db tables have been 
implemented for scheme1 at the DBMS; a properties file with jdbc and connection pool data has been created.  
For using this persistence-mysql.properties file, @PropertySource is used for turning available
this resource file to the classPath of the Maven project. Environment env is used to read and
store in memory the properties values and turn them available to objects. A SecurityDataSource bean
creator method is defined, with a ComboPooledDataSource being instantiated and set with the
properties file values, regarding jdbc and connection pool configurations, and then has that object
returned as the created and injected bean. This bean is used as an argument at the authentication configure
method at MyWebSecurityConfigurerAdapter class in order to send and retrieve registered user data to/from
the database previously created, using in-built jdbc. Different (non-default) table schemes or explicit 
jdbc/hibernate connection managing classes (DAOs) could also be used for that, if needed.
*/
