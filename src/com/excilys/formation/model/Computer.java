package com.excilys.formation.model;

import java.time.LocalDate;

public class Computer {
	private String name = "";
	private Long id = null;
	private Company company;
	private LocalDate introduced = null;
	private LocalDate discontinued = null;


// passer en privé cstr
	//dans le builder que le set
//	get de classe computer
	// class computer builder : que des set qui renvoient computerbuilder
	// avantages : que 1 seul constructeur
	
	private Computer(Long id, String name, LocalDate introduced, LocalDate discontinued, Company company) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
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

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public static class ComputerBuilder {

		private String name = "";
		private Long id = null;
		private Company company;
		private LocalDate introduced = null;
		private LocalDate discontinued = null;

		public ComputerBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public ComputerBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public ComputerBuilder setCompany(Company company) {
			this.company = company;
			return this;
		}

		public ComputerBuilder setIntroduced(LocalDate introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerBuilder setDiscontinued(LocalDate discontinued) {
			this.discontinued = discontinued;
			return this;
		}
		
		public Computer build() {
			return new Computer(id, name, introduced, discontinued, company);
		}
	}

	public String toString() {

		return "id = " + id + " name = " + name + " introduced date = " + introduced + " discontinued date = "
				+ discontinued + " company id = " + (company != null ? company.getId() : null);
	}
}