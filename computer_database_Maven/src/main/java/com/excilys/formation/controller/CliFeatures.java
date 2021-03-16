package com.excilys.formation.controller;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
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
		for (Optional<Company> c : daocp.list()) {
			System.out.println(c.orElse(null));
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
				System.out.println(daocp.showDetailsWithId(idDetails).orElse(null));
			} catch (NumberFormatException e) {
				String nameDetails = input;
				System.out.println(daocp.showDetailsWithName(nameDetails).orElse(null));
			}

			menuP.retourMenuPrincipal();
			input = sc.nextLine();
		}
	}

	public void listComputer() {
		DAOComputer daoc = new DAOComputer(con);
		for (Optional<Computer> c : daoc.list()) {
			System.out.println(c.orElse(null));
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
				System.out.println(daoc.showDetailsWithId(idDetails).orElse(null));
			} catch (NumberFormatException e) {
				String nameDetails = input;
				System.out.println(daoc.showDetailsWithName(nameDetails).orElse(null));
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
		System.out.println("Taper sur Entrer pour commencer!");
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy MM dd");
		input = sc.nextLine();
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

			menuP.retourMenuPrincipal();
			input = sc.nextLine();
		}
	}

	public void updateComputer() {

		String input;
		DAOComputer daoc = new DAOComputer(con);
		Long idAModifier;
		Company company = new Company();
		System.out.println("Taper l'id de l'ordinateur à modifier");
		idAModifier = Long.parseLong(sc.nextLine());
		System.out.println("Taper sur Entrer pour commencer!");
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy MM dd");
		while (!sc.nextLine().startsWith("0")) {
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

				Computer computer = new Computer.ComputerBuilder().setId(idAModifier).setName(name)
						.setIntroduced(introduced).setDiscontinued(discontinued).setCompany(company).build();
				daoc.updateById(idAModifier, computer);
			} catch (DateTimeParseException e) {
				System.out.println("la date n'est pas au bon format, réessayez!");
			}
			menuP.retourMenuPrincipal();
			input = sc.nextLine();
		}
	}

	public void deleteComputer() {
		System.out.println("Taper l'id de l'ordinateur à supprimer");
		String input;
		DAOComputer daoc = new DAOComputer(con);
		input = sc.nextLine();
		while (!input.startsWith("0")) {

			Long idDelete = Long.parseLong(input);
			daoc.delete(idDelete);

			menuP.retourMenuPrincipal();
			input = sc.nextLine();
		}
	}
}
