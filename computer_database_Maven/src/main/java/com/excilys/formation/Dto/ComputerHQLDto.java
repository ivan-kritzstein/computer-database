package com.excilys.formation.Dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "computer")
public class ComputerHQLDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "introduced")
	private LocalDate introduced;
	@Column(name = "discontinued")
	private LocalDate discontinued;
	@ManyToOne
	@JoinColumn(name = "company_id")
	private CompanyHQLDto companyHQLDto;

	private ComputerHQLDto() {
	}
	private ComputerHQLDto(Long id, String name, LocalDate introduced, LocalDate discontinued, CompanyHQLDto companyHQLDto) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyHQLDto = companyHQLDto;
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

	public CompanyHQLDto getCompanyHQLDto() {
		return companyHQLDto;
	}

	public void setCompanyHQLDto(CompanyHQLDto companyHQLDto) {
		this.companyHQLDto = companyHQLDto;
	}
	
	public static class ComputerHQLDtoBuilder {

		private String name = "";
		private Long id = null;
		private CompanyHQLDto companyHQLDto = null;
		private LocalDate introduced = null;
		private LocalDate discontinued = null;

		public ComputerHQLDtoBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public ComputerHQLDtoBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public ComputerHQLDtoBuilder setCompanyHQLDto(CompanyHQLDto companyHQLDto) {
			this.companyHQLDto = companyHQLDto;
			return this;
		}

		public ComputerHQLDtoBuilder setIntroduced(LocalDate introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerHQLDtoBuilder setDiscontinued(LocalDate discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ComputerHQLDto build() {
			return new ComputerHQLDto(id, name, introduced, discontinued, companyHQLDto);
		}
	}

}
