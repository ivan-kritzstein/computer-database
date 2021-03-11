package com.excilys.formation.model;
//list companies 

public class Company {
	private String name = "test";
	private Long id = null;
	
	public Company() {
		
	}
	
	public Company(Long id, String name) {
		this.setId(id);
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String toString() {
		return "company id = " + id + " company name = " + name;
	}


}