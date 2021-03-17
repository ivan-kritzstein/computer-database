package com.excilys.formation.view;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.DAO.DAOComputer;
import com.excilys.formation.controller.CompanyController;
import com.excilys.formation.controller.ComputerController;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class UseMenu {

	Menu menuP = new Menu();
	Scanner sc = new Scanner(System.in);
	Connection con;
	ComputerController cliFeatures = new ComputerController();
	CompanyController companyController = new CompanyController();
	private static Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);

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
				menuP.menuPrincipal();
				break;
			case 6:
				System.out.println(Menu.SEPARATE);
				updateComputer();
				menuP.menuPrincipal();
				break;
			case 7:
				System.out.println(Menu.SEPARATE);
				deleteComputer();
				menuP.menuPrincipal();
				break;
			default:
				break;
			}
		}

	}

	public void listCompanies() {
		for (Optional<Company> c : companyController.listCompaniesController()) {
			System.out.println(c.orElse(null));
		}
	}

	public void showCompanyDetails() {
		String input;
		input = sc.nextLine();
		while (!input.startsWith("0")) {
			try {
				Long idDetails = Long.parseLong(input);
				System.out.println(companyController.showCompanyDetailsIdController(idDetails).orElse(null));
			} catch (NumberFormatException e) {
				String nameDetails = input;
				System.out.println(companyController.showCompanyDetailsNameController(nameDetails).orElse(null));
			}

			menuP.retourMenuPrincipal();
			input = sc.nextLine();
		}
	}
	
	public void listComputer() {
		Page page = new Page();
		boolean condition = true;
		
		printComputer(cliFeatures.listComputerController(), page);
		do {
		System.out.println(Menu.INSTRUCTION_LIST);;
			int entry = sc.nextInt();
			switch (entry) {
			case Menu.SUIVANT:
				page.suivant();
				printComputer(cliFeatures.listComputerController(), page);
				break;
			case Menu.PRECEDENT:
				page.precedent();
				printComputer(cliFeatures.listComputerController(), page);
				break;
			default:
				condition = !!!true;
			}
		} while (condition);
	}
	
	private void printComputer(DAOComputer daoc, Page pages) {
		for (Optional<Computer> c : cliFeatures.printComputerController(daoc, pages)) {
			System.out.println(c.orElse(null));
		}
	}
	
	public void showComputerDetails() {
		String input;
		input = sc.nextLine();
		while (!input.startsWith("0")) {
			try {
				Long idDetails = Long.parseLong(input);
				System.out.println(cliFeatures.showComputerDetailsIdController(idDetails).orElse(null));
			} catch (NumberFormatException e) {
				String nameDetails = input;
				System.out.println(cliFeatures.showComputerDetailsNameController(nameDetails).orElse(null));
			}
			menuP.retourMenuPrincipal();
			input = sc.nextLine();
		}
	}
	
	public void createComputer() {
		String input;
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
				cliFeatures.createComputerController(computer);;
			} catch (DateTimeParseException e) {
				LOGGER.error("la date n'est pas au bon format, réessayez!" + e.getMessage());
			}

			menuP.retourMenuPrincipal();
			input = sc.nextLine();
		}
	}
	
	public void updateComputer() {

		String input;
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
				cliFeatures.updateComputerController(idAModifier, computer);
			} catch (DateTimeParseException e) {
				LOGGER.error("la date n'est pas au bon format, réessayez!" + e.getMessage());
			}
			menuP.retourMenuPrincipal();
			input = sc.nextLine();
		}
	}
	public void deleteComputer() {
		System.out.println("Taper l'id de l'ordinateur à supprimer");
		String input;
		input = sc.nextLine();
		while (!input.startsWith("0")) {

			Long idDelete = Long.parseLong(input);
			cliFeatures.deleteComputerController(idDelete);

			menuP.retourMenuPrincipal();
			input = sc.nextLine();
		}
	}
}
