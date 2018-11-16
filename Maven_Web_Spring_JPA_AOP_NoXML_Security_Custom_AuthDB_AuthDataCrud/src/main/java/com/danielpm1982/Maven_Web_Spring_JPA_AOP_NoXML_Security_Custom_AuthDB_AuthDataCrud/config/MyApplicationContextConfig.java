package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud.config;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@ComponentScan(basePackages="com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB_AuthDataCrud")
@EnableWebMvc
@EnableTransactionManagement
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
	private int getIntProperty(String propertyString) { //only a helper method for this class
		return Integer.parseInt(env.getProperty(propertyString));
	}
	private Properties getHibernateProperties() { //only a helper method for this class
		Properties props = new Properties();
		props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		return props;				
	}
//	@Bean
//	public SessionFactory sessionFactory(){
//		LocalSessionFactoryBuilder localSessionFactoryBuilder = new LocalSessionFactoryBuilder(securityDataSource()); 
//		localSessionFactoryBuilder.scanPackages(env.getProperty("hibernate.packagesToScan"));
//		localSessionFactoryBuilder.addProperties(getHibernateProperties());
//		return localSessionFactoryBuilder.buildSessionFactory();
//	}
	
//	or:
	@Bean
	public SessionFactory sessionFactory(){
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean(); 
		localSessionFactoryBean.setDataSource(securityDataSource());
		localSessionFactoryBean.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		localSessionFactoryBean.setHibernateProperties(getHibernateProperties());
		try {
			localSessionFactoryBean.afterPropertiesSet();
			return localSessionFactoryBean.getObject();
		} catch (IOException e) {
			return null;
		}
	}
	@Autowired
	SessionFactory sessionFactory;
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
}
/*
This is a Configuration class that substitutes the ApplicationContext.xml :
- the <context:component-scan base-package=""></context:component-scan> is substituted by the
annotation @ComponentScan(basePackages="");
- the <mvc:annotation-driven /> is substituted by the annotation @EnableWebMvc;
- the <aop:aspectj-autoproxy /> is substituted by the annotation @EnableAspectJAutoProxy;
- the 
	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/view/" />
		<property name="suffix" value=".jsp" />
  	</bean>
  is substituted by the ViewResolver viewresolver() bean generator method, which instantiates
  an InternalResourceViewResolver object and sets the prefix and sufix, returning this object as
  the bean;
- the <mvc:resources mapping="/resources/**" location="/resources/"></mvc:resources> is substituted
by the addResourceHandlers method, overriden from the WebMvcConfigurer interface that this class 
must implement (for registering this resource handler). 
*/

/*
This class also creates the SecurityDataSource bean, for later use at the Service classes, for 
business data as well as for auth data to be stored and retrieved to/from databases (encrypted or not), using
hibernate JPA, for both. The sessionFactory beans are injected at the DAO classes used by the Service classes,
with all the above configuration implemented. 

As commented at MyFormController class, additional POM dependencies have been set; db tables have been 
implemented for scheme1 at the DBMS; a properties file with jdbc and connection pool data has been created.  
For using this persistence-mysql.properties file, @PropertySource is used for turning available
this resource file to the classPath of the Maven project. Environment env is used to read and
store in memory the properties values and turn them available to objects. A SecurityDataSource bean
creator method is defined, with a ComboPooledDataSource being instantiated and set with the
properties file values, regarding jdbc and connection pool configurations, and then has that object
returned as the created and injected bean. This bean is used as an argument to configure the sessionFactory
bean, and this is used as an argument to set the transactionManagement in this same class.
*/
