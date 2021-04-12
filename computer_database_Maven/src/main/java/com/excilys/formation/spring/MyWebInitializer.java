//
//package com.excilys.formation.spring;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration.Dynamic;
//import javax.sql.DataSource;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//
//@Configuration
//@ComponentScan(basePackages = "com.excilys.formation")
//public class MyWebInitializer implements WebApplicationInitializer {
//
//	
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//
//    	AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(MyWebInitializer.class);
//        ctx.setServletContext(servletContext);
//
//        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
//        servlet.setLoadOnStartup(1);
//        servlet.addMapping("/");
//    }
//    
//    @Bean
//	public DataSource getDataSource() {
//		HikariConfig config = new HikariConfig(DB_PROPERTIES_NAME);
//		return new HikariDataSource(config);
//	}
//
//	@Bean
//	public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		return jdbcTemplate;
//	}
//
//	@Bean
//	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(DataSource dataSource) {
//		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//		return namedParameterJdbcTemplate;
//	}
//	
//	@Bean
//	public PlatformTransactionManager txManager() {
//		return new DataSourceTransactionManager(getDataSource());
//	}
//}
//
//
//
