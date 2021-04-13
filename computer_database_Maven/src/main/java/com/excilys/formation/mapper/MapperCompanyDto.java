package com.excilys.formation.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.formation.Dto.CompanyDto;
import com.excilys.formation.model.Company;

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
}
