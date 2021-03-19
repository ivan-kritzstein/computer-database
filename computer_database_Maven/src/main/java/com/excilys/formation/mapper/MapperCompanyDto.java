package com.excilys.formation.mapper;

import com.excilys.formation.Dto.CompanyDto;
import com.excilys.formation.model.Company;

public class MapperCompanyDto {

	public static CompanyDto companyToCompanyDto(Company company) {
		CompanyDto cmpnDto = new CompanyDto();
		String id = null;
		String name = null;
		if (company != null) {
			if (company.getId() != null) {
				id = company.getId().toString();
			}
			if (company.getName() != null) {
				name = company.getName();
			}
			cmpnDto = new CompanyDto(id, name);
		}
		return cmpnDto;
	}
}
