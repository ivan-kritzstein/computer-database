package com.excilys.formation.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.controller.ComputerController;
import com.excilys.formation.view.UseMenu;

public class Main {

	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(ComputerController.class);
	    logger.error("Infos sur la classe");
		UseMenu useMP = new UseMenu();
		useMP.useMenuP();
	}

}

// taches a faire :          
//page pour companies 
//Logger pour tous les try catch
// refacto des sysout dans le code 
// enlever magic string et magic number
//ajouter classfor name dans la connection
// bouger la co de place : en phase avec archi mvc