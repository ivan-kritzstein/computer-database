package com.excilys.formation.model;

import java.io.FileNotFoundException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.formation.spring.WebConfig;
import com.excilys.formation.view.UseMenu;

public class Main {

	UseMenu useMenu;
	@Autowired
	public Main (UseMenu useMenu) {
		this.useMenu = useMenu;
	}
	
	public static void main(String[] args) throws SQLException, FileNotFoundException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
		UseMenu useMenu = context.getBean(UseMenu.class);
		useMenu.useMenuP();
		((ConfigurableApplicationContext) context).close();
	}
	

}

// taches a faire :          
// refacto des sysout dans le code 
// enlever magic string et magic number 
// enlever le null de AddComputerServlet dans cmpnDto
// faire fichier avec versions des dependences 
// changer optional or else null
// Autowired dans mappercompanyDto ??? 
// scope des bean?





