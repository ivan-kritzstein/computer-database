package com.excilys.formation.view;

public class Page {
	private int offset = 0;
	private int limit = 10;

	public void suivant() {
		offset += limit;
	}

	public void precedent() {
		if (offset > 0) {
			offset -= limit;
		}
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

}
