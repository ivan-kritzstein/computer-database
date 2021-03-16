package com.excilys.formation.view;

public class Menu {

	public static final String SEPARATE = "-----------------------------------------------";
	public static final String RETOUR_MENU_P = "Tapez 0 pour retourner au menu principal";
	public static final String CHOICE_1 = "Taper 1 : Afficher les companies";
	public static final String CHOICE_2 = "Taper 2 : Afficher détails d'une seule companie";
	public static final String CHOICE_3 = "Taper 3 : Afficher les ordinateurs";
	public static final String CHOICE_4 = "Taper 4 : Affiher détails d'un seul ordinateur";
	public static final String CHOICE_5 = "Taper 5 : Créer un nouvel ordinateur";
	public static final String CHOICE_6 = "Taper 6 : Modifier un ordinateur";
	public static final String CHOICE_7 = "Taper 7 : Supprimer un ordinateur";

	public void menuPrincipal() {
		System.out.println(CHOICE_1);
		System.out.println(SEPARATE);
		System.out.println(CHOICE_2);
		System.out.println(SEPARATE);
		System.out.println(CHOICE_3);
		System.out.println(SEPARATE);
		System.out.println(CHOICE_4);
		System.out.println(SEPARATE);
		System.out.println(CHOICE_5);
		System.out.println(SEPARATE);
		System.out.println(CHOICE_6);
		System.out.println(SEPARATE);
		System.out.println(CHOICE_7);
	}
// transformer methode en constante et les appeler dans useMenu
	public void retourMenuPrincipal() {
		System.out.println(SEPARATE);
		System.out.println(RETOUR_MENU_P);
	}

	public void menuShowCompanyDetails() {
		System.out.println(SEPARATE);
		System.out.println("Entrez un id ou un nom de company");
	}

	public void menuShowComputerDetails() {
		System.out.println(SEPARATE);
		System.out.println("Entrez un id ou un nom d'ordinateur");
	}

	public void menuCreateComputer() {
		System.out.println(SEPARATE);
		System.out.println("Entrez les caractéristiques d'un nouvel ordinateur");
	}

	public void createInstructions1() {
		System.out.println(SEPARATE);
		System.out.println("Veuillez entrer le nom de l'ordinateur");
	}

	public void createInstructions2() {
		System.out.println(SEPARATE);
		System.out.println("Veuillez entrer la date (yyyy MM dd) d'introduction de l'ordinateur");
	}

	public void createInstructions3() {
		System.out.println(SEPARATE);
		System.out.println("Veuillez entrer la date d'arrêt de l'ordinateur");
	}

	public void createInstructions4() {
		System.out.println(SEPARATE);
		System.out.println("Veuillez entrer l'id de la company de l'ordinateur");
	}

}