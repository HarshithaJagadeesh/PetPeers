package com.hcl.cs.config;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.hcl.cs.model.Pet;
import com.hcl.cs.model.User;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com" })
@EnableTransactionManagement
public class PetConfig implements WebMvcConfigurer{
		@Bean
		public InternalResourceViewResolver viewResolver() {
			InternalResourceViewResolver resolver = new InternalResourceViewResolver();
			resolver.setPrefix("/WEB-INF/jsp/");
			resolver.setSuffix(".jsp");
			return resolver;
		}

		@Bean
		public DataSource getDataSource() {
			BasicDataSource dataSource = new BasicDataSource();
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://localhost:3306/petpeers?useLegacyDatetimeCode=false");
			dataSource.setUsername("root");
			dataSource.setPassword("iam19196");
			return dataSource;
		}

		@Bean
		public LocalSessionFactoryBean getsessionFactory() {
			LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
			factoryBean.setDataSource(getDataSource());
			Properties prop = new Properties();
			String dialect = "org.hibernate.dialect.MySQL57Dialect";
			prop.setProperty("hibernate.hbm2ddl.auto", "update");
			prop.setProperty("hibernate.show_sql", "true");
			prop.setProperty("hibernate.dialect", dialect);
			factoryBean.setHibernateProperties(prop);
			factoryBean.setAnnotatedClasses(User.class, Pet.class);
			return factoryBean;
		}

		@Bean
		public HibernateTransactionManager getTransactionManager() {
			HibernateTransactionManager transaction = new HibernateTransactionManager();
			transaction.setSessionFactory(getsessionFactory().getObject());
			return transaction;
		}
		
		

	

}
