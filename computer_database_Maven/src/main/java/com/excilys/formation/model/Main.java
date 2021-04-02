package com.excilys.formation.model;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.view.UseMenu;

public class Main {

	public static void main(String[] args) {

		UseMenu useMP = new UseMenu();
		useMP.useMenuP();
		
	}

}

// taches a faire :          
//page pour companies 
// refacto des sysout dans le code 
// enlever magic string et magic number
// bouger la co de place : en phase avec archi mvc
// enlever le null de AddComputerServlet dans cmpnDto
// faire fichier avec versions des dependences
//request.getparameter(selection) puis appliquer le split
// changer optional or else null