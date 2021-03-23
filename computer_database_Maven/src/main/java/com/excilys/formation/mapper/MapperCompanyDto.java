package com.excilys.formation.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.formation.Dto.CompanyDto;
import com.excilys.formation.Dto.ComputerDto;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

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

	public static List<CompanyDto> companyToListCompanyDto(List<Optional<Company>> listOC) {
		List<CompanyDto> list = new ArrayList<CompanyDto>();

		for (Optional<Company> c : listOC) {
			Company company = c.orElse(null);
			if (company != null) {
				list.add(companyToCompanyDto(company));
			}
		}

		return list;
	}

	public static Company companyDtoToCompany(CompanyDto companyDto) {
		Company company = new Company();
		Long id = null;
		String name = null;

		if (companyDto.getId() != null) {
		id = Long.parseLong(companyDto.getId());
		}
		name = companyDto.getName();

		company = new Company(id, name);
		return company;
	}
}
