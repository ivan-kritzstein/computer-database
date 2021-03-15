package com.excilys.formation.controller;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.excilys.formation.DAO.DAOCompany;
import com.excilys.formation.DAO.DAOComputer;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
import com.excilys.formation.view.Menu;

public class CliFeatures {

	Menu menuP = new Menu();
	Scanner sc = new Scanner(System.in);
	Connection con;

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
		company.setId(null);
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
				input = sc.nextLine();
				if (input != "") {
					company.setId(Long.parseLong(input));
				} 

				Computer computer = new Computer.ComputerBuilder().setId(id).setName(name).setIntroduced(introduced)
						.setDiscontinued(discontinued).setCompany(company).build();
				daoc.create(computer);
			} catch (DateTimeParseException e) {
				System.out.println("la date n'est pas au bon format, réessayez!");
			}

		}
	}

	public void updateComputer() {

	}
}
