package com.excilys.formation.model;

import java.time.LocalDate;

public class Computer {
	private String name = "";
	private Long id = null;
	private Long company_id = null;
	private LocalDate introduced = null;
	private LocalDate discontinued = null;

	public Computer() {

	}

	public Computer(Long id, String name, LocalDate introduced, LocalDate discontinued, Long company_id) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
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

	public Long getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Long company_id) {
		this.company_id = company_id;
	}

	public String toString() {

		return "id = " + id + " name = " + name + " introduced date = " + introduced + " discontinued date = "
				+ discontinued + " company id = " + company_id;
	}
}