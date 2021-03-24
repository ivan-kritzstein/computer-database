package com.excilys.formation.Dto;

public class ComputerDto {

	private String id;
	private String name;
	private CompanyDto companyDto;
	//private String companyName;
	private String introduced;
	private String discontinued;
	
	private ComputerDto(String id, String name, String introduced, String discontinued, CompanyDto companyDto) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyDto = companyDto;
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
	public CompanyDto getCompanyDto() {
		return companyDto;
	}
	public void setCompanyDto(CompanyDto companyDto) {
		this.companyDto = companyDto;
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

	public static class ComputerDtoBuilder {

		private String id;
		private String name;
		private CompanyDto companyDto;
		private String introduced;
		private String discontinued;
		
		public ComputerDtoBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public ComputerDtoBuilder setCompanyDto(CompanyDto companyDto) {
			this.companyDto = companyDto;
			return this;
		}

		public ComputerDtoBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public ComputerDtoBuilder setIntroduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerDtoBuilder setDiscontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}
		
		public ComputerDto build() {
			return new ComputerDto(id, name, introduced, discontinued, companyDto);
		}
				

	}

	@Override
	public String toString() {
		return "ComputerDto [id=" + id + ", name=" + name  + ", introduced="
				+ introduced + ", discontinued=" + discontinued +  companyDto.toString() + "]";
	}
	
}
