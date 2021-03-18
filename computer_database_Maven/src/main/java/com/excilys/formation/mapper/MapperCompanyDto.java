package com.excilys.formation.mapper;

import com.excilys.formation.Dto.CompanyDto;
import com.excilys.formation.model.Company;

public class MapperCompanyDto {

	public static CompanyDto companyToCompanyDto(Company company) {
		CompanyDto cmpnDto = new CompanyDto();

		String id = company.getId().toString();
		String name = company.getName().toString();

		cmpnDto = new CompanyDto(id, name);

		return cmpnDto;
	}
}
