package com.excilys.formation.ui;

import java.sql.*;
import java.util.Scanner;

import com.excilys.formation.model.*;

public class UseMenu {

	Menu menuP = new Menu();
	Scanner sc = new Scanner(System.in);
	Connection con;


	public UseMenu () {
		// ouvre menu 1
		Data data = new Data ();
		try {
			con = data.getConnection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void useMenuP() {
		menuP.menuPrincipal();
		int userEntry = 0;
		while(userEntry!=100) {

			userEntry = Integer.parseInt(sc.nextLine());

			switch (userEntry) {
			case 0 : menuP.menuPrincipal();
			break;

			case 1 : 
				listCompanies();
				menuP.retourMenuPrincipal();
				break;

			case 2 : 
				menuP.menuShowCompanyDetails();
				showCompanyDetails();
				menuP.menuPrincipal();

				break;
			case 3 : 
				listComputer();
				menuP.retourMenuPrincipal();
				break;
			case 4 : 
				menuP.menuShowComputerDetails();
				showComputerDetails();
				menuP.menuPrincipal();

				break;
			case 5 : 
				menuP.menuCreateComputer();
				break;
			case 6 : 

				break;
			case 7 : 

				break;
			default :
				break;
			}
		}
		sc.close();

		//		DAOComputer daoc = new DAOComputer (con);
		//		DAOCompany daocp = new DAOCompany(con);
		//		Company comp = new Company (44, "company test");
		//		daocp.updateByName("company test", comp);
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// création et utilisation Scanner


	}

	public void listCompanies() {
		DAOCompany daocp = new DAOCompany(con);
		for (Company c : daocp.list()) {
			System.out.println(c);
		}
		//DAOCompany daocp = 
	}

	public void showCompanyDetails() {
		String input;
		DAOCompany daocp = new DAOCompany(con);
		input = sc.nextLine();
		while (!input.startsWith("0")){
			try {
				int idDetails = Integer.parseInt(input);
				System.out.println(daocp.showDetailsWithId(idDetails));
			}
			catch (NumberFormatException e) {
				String nameDetails = input;
				System.out.println(daocp.showDetailsWithName(nameDetails));	
			}

			menuP.retourMenuPrincipal();
			input = sc.nextLine();
		}
	}

	public void listComputer() {
		DAOComputer daoc = new DAOComputer(con);
		for (Computer c : daoc.list()) {
			System.out.println(c);
		}
		//DAOCompany daocp = 
	}
	
	public void showComputerDetails() {
		String input;
		DAOComputer daoc = new DAOComputer(con);
		input = sc.nextLine();
		while (!input.startsWith("0")){
			try {
				int idDetails = Integer.parseInt(input);
				System.out.println(daoc.showDetailsWithId(idDetails));
			}
			catch (NumberFormatException e) {
				String nameDetails = input;
				System.out.println(daoc.showDetailsWithName(nameDetails));	
			}

			menuP.retourMenuPrincipal();
			input = sc.nextLine();
		}
	}
	
	public void createComputer() {
		String input;
		DAOComputer daoc = new DAOComputer(con);
		
		input = sc.nextLine();
		while (!input.startsWith("0")){
			Computer computer = new Computer(null,input,Date.parse(input),Date.parse(input),Integer.parseInt(input));
		}
	}


	//	Menu menu1 = new Menu();
	//	Scanner sc = new Scanner(System.in);
	//	String string = sc.nextLine();
	//	
	//	System.out.println(string+string);
	//	
	//	sc.close();
}
