package com.excilys.formation.model;
//list companies 

public class Company {
	private String name = "test";
	private int id = 0;
	
	public Company() {
		
	}
	
	public Company(int id, String name) {
		this.setId(id);
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return "company id = " + id + " company name = " + name;
	}


}