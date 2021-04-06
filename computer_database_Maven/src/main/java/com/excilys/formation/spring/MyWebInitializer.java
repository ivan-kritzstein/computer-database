//package com.excilys.formation.spring;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration.Dynamic;
//
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = "com.excilys.formation")
//public class MyWebInitializer implements WebApplicationInitializer, WebMvcConfigurer {
//	
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//
//    	AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(WebConfig.class);
//        ctx.setServletContext(servletContext);
//
//        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
//        servlet.setLoadOnStartup(1);
//        servlet.addMapping("/");
//    }
//    
//    @Override
//    public void addResourceHandlers( ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//	}
//    
//    public ViewResolver configureViewResolver() {
//	     InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//	     viewResolver.setPrefix("/WEB-INF/lib/views/");
//	     viewResolver.setSuffix(".jsp");
//	 
//	     return viewResolver;
//	 }
//}



