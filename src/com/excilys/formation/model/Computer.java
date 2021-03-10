package com.excilys.formation.model;

import java.sql.Date;

public class Computer {
	private String name = "";
	private int id = 0;
	private int company_id = 1;
	private Date introduced = null;
	private Date discontinued = null;

	public Computer () {

	}

	public Computer(int id, String name, Date introduced, Date discontinued, int company_id) {
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getIntroduced() {
		return introduced;
	}
	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}
	public Date getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public String toString() {

		return "id = " + id + " name = " + name + " introduced date = " + introduced + " discontinued date = " + discontinued + " company id = "+ company_id;
	}
}