package com.excilys.formation.view;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.excilys.formation.controller.CliFeatures;
import com.excilys.formation.model.Data;

public class UseMenu {

	Menu menuP = new Menu();
	Scanner sc = new Scanner(System.in);
	Connection con;
	CliFeatures cliFeatures = new CliFeatures();

	public UseMenu() {
		Data data = Data.getInstance();
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
				cliFeatures.listCompanies();
				menuP.retourMenuPrincipal();
				break;

			case 2:
				menuP.menuShowCompanyDetails();
				cliFeatures.showCompanyDetails();
				menuP.menuPrincipal();

				break;
			case 3:
				cliFeatures.listComputer();
				menuP.retourMenuPrincipal();
				break;
			case 4:
				menuP.menuShowComputerDetails();
				cliFeatures.showComputerDetails();
				menuP.menuPrincipal();

				break;
			case 5:
				menuP.menuCreateComputer();
				cliFeatures.createComputer();
				menuP.menuPrincipal();
				break;
			case 6:
				System.out.println(Menu.SEPARATE);
				cliFeatures.updateComputer();
				menuP.menuPrincipal();
				break;
			case 7:
				System.out.println(Menu.SEPARATE);
				cliFeatures.deleteComputer();
				menuP.menuPrincipal();
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

	}
}
