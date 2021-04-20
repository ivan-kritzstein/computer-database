package com.excilys.formation.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.formation.Dto.CompanyDto;
import com.excilys.formation.Dto.CompanyHQLDto;
import com.excilys.formation.Dto.ComputerHQLDto;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class MapperCompanyDto {

	public static CompanyDto toCompanyDto(Company company) {
		CompanyDto companyDto = new CompanyDto.CompanyDtoBuilder().build();

		String name = null;
		String id = null;

		if (company.getId() != null) {
			id = company.getId().toString();
		}

		if (company.getName() != null) {
			name = company.getName();
		}

		companyDto = new CompanyDto.CompanyDtoBuilder().setName(name).setId(id).build();

		return companyDto;
	}

	public static List<CompanyDto> listOptionalCompanyToListCompanyDto(List<Optional<Company>> listOC) {
		List<CompanyDto> list = new ArrayList<CompanyDto>();

		for (Optional<Company> c : listOC) {
			Company company = c.orElse(null);
			if (company != null) {
				list.add(toCompanyDto(company));

			}
		}
		return list;
	}

	public static CompanyHQLDto companyToCompanyHQLDto(Company company) {

		CompanyHQLDto companyHQLDto = new CompanyHQLDto();

		if (company != null && company.getId()!= null && company.getName() != null) {
			companyHQLDto.setId(company.getId());
			companyHQLDto.setName(company.getName());
			
			return companyHQLDto;
		} else {
			return null;
		}	
	}
	
	public static Optional<Company> companyHQLDtoToCompany(CompanyHQLDto companyHQLDto) {
		Company company = new Company();
		Long id = null;
		String name = null;


		if (companyHQLDto.getId() != null) {
			id = companyHQLDto.getId();
			company.setId(id);
		}

		if (null != companyHQLDto.getName()) {
			name = companyHQLDto.getName();
			company.setName(name);
		}
		

		return Optional.ofNullable(company);
	}
}
