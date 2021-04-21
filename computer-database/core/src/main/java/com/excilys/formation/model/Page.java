package com.excilys.formation.model;

import org.springframework.stereotype.Component;

@Component
public class Page {
	private static final int ZERO = 0;
	private static final int UN = 1;
	private static final int DEUX = 2;
	private int offset = 0;
	private int limit = 10;
	private int nbrComputer;
	private String searchComputer = "";
	private String orderBy = "computer.id";
	private int index1 = 1;
	private int index2 = 2;
	private int index3 = 3;
	private int index4 = 4;
	private int index5 = 5;

	public void suivant() {
		offset += limit;
	}

	public void precedent() {
		if (offset > ZERO) {
			offset -= limit;
		}
	}

	public void indexPage() { 
		index3 = offset / limit;
		if (offset == ZERO) {
			index3 += DEUX;
		} else if (offset == limit) {
			index3 += UN;
		} else if (offset >= nbrComputer - limit) {
			index3 -= DEUX;
		} else if (offset >= nbrComputer - DEUX * limit) {
			index3 -= UN;
		} 
		index1 = (index3 - DEUX);
		index2 = (index3 - UN);
		index4 = (index3 + UN);
		index5 = (index3 + DEUX);
	}


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

	public String getSearchComputer() {
		return searchComputer;
	}

	public void setSearchComputer(String searchComputer) {
		this.searchComputer = searchComputer;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public int getIndex1() {
		return index1;
	}

	public void setIndex1(int index1) {
		this.index1 = index1;
	}

	public int getIndex2() {
		return index2;
	}

	public void setIndex2(int index2) {
		this.index2 = index2;
	}
	
	public int getIndex3() {
		return index3;
	}

	public void setIndex3(int index3) {
		this.index3 = index3;
	}

	public int getIndex4() {
		return index4;
	}

	public void setIndex4(int index4) {
		this.index4 = index4;
	}

	public int getIndex5() {
		return index5;
	}

	public void setIndex5(int index5) {
		this.index5 = index5;
	}

}
