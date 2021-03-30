package com.excilys.formation.Dto;

public class EditDto {

	private String id;
	private String name;
	private String companyId;
	// private String companyName;
	private String introduced;
	private String discontinued;

	private EditDto(String id, String name, String introduced, String discontinued, String companyId) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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

	public static class EditDtoBuilder {

		private String id;
		private String name;
		private String companyId;
		private String introduced;
		private String discontinued;

		public EditDtoBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public EditDtoBuilder setCompanyId(String companyName) {
			this.companyId = companyName;
			return this;
		}

		public EditDtoBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public EditDtoBuilder setIntroduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		public EditDtoBuilder setDiscontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public EditDto build() {
			return new EditDto(id, name, introduced, discontinued, companyId);
		}

	}
}
