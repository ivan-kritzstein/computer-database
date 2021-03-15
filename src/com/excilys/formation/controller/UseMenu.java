package com.excilys.formation.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.excilys.formation.DAO.DAOCompany;
import com.excilys.formation.DAO.DAOComputer;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
import com.excilys.formation.model.Data;
import com.excilys.formation.view.Menu;

public class UseMenu {

	Menu menuP = new Menu();
	Scanner sc = new Scanner(System.in);
	Connection con;

	public UseMenu() {
		// ouvre menu 1
		Data data = new Data();
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
		while (userEntry != 100) {

			userEntry = Integer.parseInt(sc.nextLine());

			switch (userEntry) {
			case 0:
				menuP.menuPrincipal();
				break;

			case 1:
				listCompanies();
				menuP.retourMenuPrincipal();
				break;

			case 2:
				menuP.menuShowCompanyDetails();
				showCompanyDetails();
				menuP.menuPrincipal();

				break;
			case 3:
				listComputer();
				menuP.retourMenuPrincipal();
				break;
			case 4:
				menuP.menuShowComputerDetails();
				showComputerDetails();
				menuP.menuPrincipal();

				break;
			case 5:
				menuP.menuCreateComputer();
				createComputer();
				break;
			case 6:

				break;
			case 7:

				break;
			default:
				break;
			}
		}
		sc.close();
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
		// DAOCompany daocp =
	}

	public void showCompanyDetails() {
		String input;
		DAOCompany daocp = new DAOCompany(con);
		input = sc.nextLine();
		while (!input.startsWith("0")) {
			try {
				Long idDetails = Long.parseLong(input);
				System.out.println(daocp.showDetailsWithId(idDetails));
			} catch (NumberFormatException e) {
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
		// DAOCompany daocp =
	}

	public void showComputerDetails() {
		String input;
		DAOComputer daoc = new DAOComputer(con);
		input = sc.nextLine();
		while (!input.startsWith("0")) {
			try {
				Long idDetails = Long.parseLong(input);
				System.out.println(daoc.showDetailsWithId(idDetails));
			} catch (NumberFormatException e) {
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
		Long id = null;
		Company company = new Company();
		input = sc.nextLine();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy MM dd");
		while (!input.startsWith("0")) {
			try {
				menuP.createInstructions1();
				String name = sc.nextLine();
				menuP.createInstructions2();

				LocalDate introduced = LocalDate.parse(sc.nextLine(), df);
				menuP.createInstructions3();

				LocalDate discontinued = LocalDate.parse(sc.nextLine(), df);
				menuP.createInstructions4();
				company.setId(Long.parseLong(sc.nextLine()));

				Computer computer = new Computer.ComputerBuilder().setId(id).setName(name).setIntroduced(introduced)
						.setDiscontinued(discontinued).setCompany(company).build();
				daoc.create(computer);
			} catch (DateTimeParseException e) {
				System.out.println("la date n'est pas au bon format, réessayez!");
			}

		}
	}
}
