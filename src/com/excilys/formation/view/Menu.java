package com.excilys.formation.view;

public class Menu {
	
	private static final String SEPARATE = "-----------------------------------------------";
	public static final String RETOUR_MENU_P = "Tapez 0 pour retourner au menu principal";
	
	public void menuPrincipal() {
		System.out.println("Taper 1 : Afficher les companies");
		System.out.println(SEPARATE);
		System.out.println("Taper 2 : Afficher détails d'une seule companie");
		System.out.println(SEPARATE);
		System.out.println("Taper 3 : Afficher les ordinateurs");
		System.out.println(SEPARATE);
		System.out.println("Taper 4 : Affiher détails d'un seul ordinateur");
		System.out.println(SEPARATE); 
		System.out.println("Taper 5 : Créer un nouvel ordinateur");
		System.out.println(SEPARATE);
		System.out.println("Taper 6 : Modifier un ordinateur");
		System.out.println(SEPARATE); 
		System.out.println("Taper 7 : Supprimer un ordinateur");
	}
	
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
	
	public void createInstructions1 () {
		System.out.println(SEPARATE);
		System.out.println("Veuillez entrer le nom de l'ordinateur à créer");
	}
	public void createInstructions2 () {
		System.out.println(SEPARATE);
		System.out.println("Veuillez entrer la date (yyyy MM dd) d'introduction de l'ordinateur à créer");
	}
	public void createInstructions3 () {
		System.out.println(SEPARATE);
		System.out.println("Veuillez entrer la date d'arrêt de l'ordinateur à créer");
	}
	public void createInstructions4 () {
		System.out.println(SEPARATE);
		System.out.println("Veuillez entrer l'id de la company de l'ordinateur à créer");
	}
	
}	