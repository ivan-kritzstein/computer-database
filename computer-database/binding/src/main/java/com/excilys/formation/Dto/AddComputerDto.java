package com.excilys.formation.Dto;

public class AddComputerDto {

	private String id;
	private String name;
	private String introduced;
	private String discontinued;
	private String company_id;

	private AddComputerDto(String id, String name, String introduced, String discontinued, String companyId) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = companyId;
	}

	public AddComputerDto() {
		// TODO Auto-generated constructor stub
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

	public String getCompany_id() {
		return company_id;
	}

	public void setCompanyId(String companyId) {
		this.company_id = companyId;
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

	public static class AddComputerDtoBuilder {

		private String id;
		private String name;
		private String company_id;
		private String introduced;
		private String discontinued;

		public AddComputerDtoBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public AddComputerDtoBuilder setCompany_id(String companyName) {
			this.company_id = companyName;
			return this;
		}

		public AddComputerDtoBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public AddComputerDtoBuilder setIntroduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		public AddComputerDtoBuilder setDiscontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public AddComputerDto build() {
			return new AddComputerDto(id, name, introduced, discontinued, company_id);
		}

	}
}
