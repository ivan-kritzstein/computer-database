package com.excilys.formation.view;

public class Page {
	private int offset = 0;
	private int limit = 10;
	private int nbrComputer;
//	private int numeroPage;
//	private final int CALCUL_NUMERO_PAGE = 1;
//	int numero1;
//	int numero2;
//	int numero3;
//	int numero4;
//	int numero5;
	
	public void suivant() {
		offset += limit;
	}

	public void precedent() {
		if (offset > 0) {
			offset -= limit;
		}
	}
	
//	public void numeroPage() {
//		numeroPage = offset/limit + CALCUL_NUMERO_PAGE;
//		
//		if () {
//			
//		}
//		
//	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getNbrComputer() {
		return nbrComputer;
	}

	public void setNbrComputer(int nbrComputer) {
		this.nbrComputer = nbrComputer;
	}

}
