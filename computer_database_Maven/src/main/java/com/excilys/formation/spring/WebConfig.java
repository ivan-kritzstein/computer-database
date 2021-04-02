package com.excilys.formation.spring;

//
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Configuration
//@EnableWebMvc
@ComponentScan(basePackages = "com.excilys.formation")

public class WebConfig extends AbstractContextLoaderInitializer{

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		// TODO Auto-generated method stub
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebConfig.class);
//        ctx.refresh();
		return ctx;
	}
}
	
	
//	@Bean
//    public UrlBasedViewResolver setupViewResolver() {
//        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
//        resolver.setPrefix("/WebContent");
//        resolver.setSuffix(".js");
//        resolver.setViewClass(JstlView.class);
//        return resolver;
//    }
	
//	 @Override
//	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
//	        registry.addResourceHandler("/css/**").addResourceLocations("/fonts/").setCachePeriod(31556926);
//	        registry.addResourceHandler("/img/**").addResourceLocations("/image/").setCachePeriod(31556926);
//	        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
//	    }
	
//	protected String[] getServletMappings() {
//        return new String[] { "/" };
//    }

