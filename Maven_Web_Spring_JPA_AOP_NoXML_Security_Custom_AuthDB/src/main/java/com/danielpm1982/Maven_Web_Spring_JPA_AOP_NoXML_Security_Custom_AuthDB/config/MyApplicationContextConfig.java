package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB.config;
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
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB.entity.EntityModel;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@ComponentScan(basePackages="com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB")
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
	private int getIntProperty(String propertyString) {
		return Integer.parseInt(env.getProperty(propertyString));
	}
	private Properties getHibernateProperties() {
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
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
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

/*
For implementing ORM Persistence for the main entity classes, EntityModel class in this case, two other beans,
SessionFactory and HibernateTransactionManager, are added at this ApplicationContext class, reusing
the same SecurityDataSource already defined for the Authentication (User login data), but now for persisting
the entities. Hibernate properties are added to the same persistence-mysql.properties file and obtained,
using the env bean, to the SessionFactory, other than other properties set directly (dataSource and packagesToScan).
There are at least two objects classes from which one can obtain the SessionFactory, which are 
LocalSessionFactoryBuilder and LocalSessionFactoryBean, as shown above. The bean creational method must
return, either way, a SessionFactory, which will be injected at the DAO objects. The LocalSessionFactoryBuilder
bean creational method must be declared (manual transaction doesn't work here), and @EnableTransactionManagement
must be set. At the Service classes, the @Transactional is used for each method. See more info at EntityModelDAO 
class.  
*/
