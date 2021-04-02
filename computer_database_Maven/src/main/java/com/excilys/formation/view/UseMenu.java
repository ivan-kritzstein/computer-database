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
	ComputerController computerController = new ComputerController();
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
			case 8:
				System.out.println(Menu.SEPARATE);
				deleteCompanyAndComputersAssociated();
				menuP.menuPrincipal();
			default:
				break;
			}
		}

	}

	public void listCompanies() {
		System.out.println(companyController);
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

		printComputer(computerController.listComputerController(), page);
		do {
			System.out.println(Menu.INSTRUCTION_LIST);
			;
			int entry = sc.nextInt();
			switch (entry) {
			case Menu.SUIVANT:
				page.suivant();
				printComputer(computerController.listComputerController(), page);
				break;
			case Menu.PRECEDENT:
				page.precedent();
				printComputer(computerController.listComputerController(), page);
				break;
			default:
				condition = !!!true;
			}
		} while (condition);
	}

	private void printComputer(DAOComputer daoc, Page pages) {
		for (Optional<Computer> c : computerController.printComputerController( pages)) {
			System.out.println(c.orElse(null));
		}
	}

	public void showComputerDetails() {
		String input;
		input = sc.nextLine();
		while (!input.startsWith("0")) {
			try {
				Long idDetails = Long.parseLong(input);
				System.out.println(computerController.showComputerDetailsIdController(idDetails).orElse(null));
			} catch (NumberFormatException e) {
				String nameDetails = input;
				System.out.println(computerController.showComputerDetailsNameController(nameDetails).orElse(null));
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
				LocalDate introduced = null;
				input = sc.nextLine();
				if (input != "") {
					introduced = LocalDate.parse(input, df);
				}

				menuP.createInstructions3();
				LocalDate discontinued = null;
				input = sc.nextLine();
				if (input != "") {
					discontinued = LocalDate.parse(input, df);
				}
				menuP.createInstructions4();
				input = sc.nextLine();
				if (input != "") {
					company.setId(Long.parseLong(input));
				}

				Computer computer = new Computer.ComputerBuilder().setId(id).setName(name).setIntroduced(introduced)
						.setDiscontinued(discontinued).setCompany(company).build();
				computerController.createComputerController(computer);
			} catch (DateTimeParseException e) {
				LOGGER.error("la date n'est pas au bon format, réessayez!" + e.getMessage());
				e.printStackTrace();
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
				computerController.updateComputerController(idAModifier, computer);
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
			computerController.deleteComputerController(idDelete);

			menuP.retourMenuPrincipal();
			input = sc.nextLine();
		}
	}
	
	public void deleteCompanyAndComputersAssociated() {
		System.out.println("Taper l'id de la companie à supprimer, tous les ordinateurs associés seront supprimés également");
		String input;
		input = sc.nextLine();
		while (!input.startsWith("0")) {

			Long idDelete = Long.parseLong(input);
			companyController.deleteCompanyAndComputersAssociatedController(idDelete);

			menuP.retourMenuPrincipal();
			input = sc.nextLine();
		}
	}
}
