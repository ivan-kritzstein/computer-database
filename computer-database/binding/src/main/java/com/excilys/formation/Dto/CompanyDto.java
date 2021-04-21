package com.excilys.formation.Dto;

public class CompanyDto {

	private String id;
	private String name;
	private CompanyDto(String id, String name) {

		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static class CompanyDtoBuilder {

		private String name;
		private String id;

		public CompanyDtoBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public CompanyDtoBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public CompanyDto build() {
			return new CompanyDto(id, name);
		}

	}

	@Override
	public String toString() {
		return "ListComputerDto [" + "company name= " + name + ", company id=" + id + "]";
	}
}
