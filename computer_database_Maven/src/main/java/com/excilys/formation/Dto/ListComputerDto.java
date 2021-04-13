package com.excilys.formation.Dto;

public class ListComputerDto {

	private String id;
	private String name;
	private String companyName;
	// private String companyName;
	private String introduced;
	private String discontinued;

	private ListComputerDto(String id, String name, String introduced, String discontinued, String companyName) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyName = companyName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public static class ListComputerDtoBuilder {

		private String id;
		private String name;
		private String companyName;
		private String introduced;
		private String discontinued;

		public ListComputerDtoBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public ListComputerDtoBuilder setCompanyName(String companyName) {
			this.companyName = companyName;
			return this;
		}

		public ListComputerDtoBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public ListComputerDtoBuilder setIntroduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		public ListComputerDtoBuilder setDiscontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ListComputerDto build() {
			return new ListComputerDto(id, name, introduced, discontinued, companyName);
		}

	}

	@Override
	public String toString() {
		return "ListComputerDto [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", company name= " + companyName + "]";
	}

}