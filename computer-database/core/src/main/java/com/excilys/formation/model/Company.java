package com.excilys.formation.model;

public class Company {

	private Long id;
	private String name;

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