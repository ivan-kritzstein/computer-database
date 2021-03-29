package com.excilys.formation.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.formation.Dto.CompanyDto;
import com.excilys.formation.model.Company;

public class MapperCompanyDto {

	public static CompanyDto toCompanyDto(Company company) {
		CompanyDto CompanyDto = new CompanyDto.CompanyDtoBuilder().build();

		String name = null;
		String id = null;


		if(company.getId() != null) {
			id = company.getId().toString();
		}

		if(company.getName() != null) {
			name = company.getName();
		}
	

//		company = computer.getCompany();
//		companyDto = MapperCompanyDto.companyToCompanyDto(company);

		CompanyDto = new CompanyDto.CompanyDtoBuilder().setName(name).setId(id).build();

		return CompanyDto;
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

	}}

//	public static Optional<Computer> addComputerDtoToComputer(AddComputerDto addComputerDto) {
//		Computer computer = new Computer.ComputerBuilder().build();
//
//		String name = null;
//		LocalDate introduced = null;
//		LocalDate discontinued = null;
////		String companyDto = new String();
//		Company company = new Company();
//		
//		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//
//		if (null != addComputerDto.getName()) {
//			name = addComputerDto.getName();
//		}
//
//		if (null != addComputerDto.getIntroduced() && addComputerDto.getIntroduced().compareTo("") != 0) {
//			introduced = LocalDate.parse(addComputerDto.getIntroduced(), df);
//		}
//		if (addComputerDto.getDiscontinued() != null && addComputerDto.getDiscontinued().compareTo("") != 0) {
//			discontinued = LocalDate.parse(addComputerDto.getDiscontinued(), df);
//		}
//		if (addComputerDto.getCompanyName() != null) {
//			company.setName(addComputerDto.getCompanyName());
//		}
//		if(addComputerDto.getId() != null) {
//			company.setId(Long.parseLong(addComputerDto.getId()));
//		}
////		company = MapperCompanyDto.companyDtoToCompany(companyDto);
//
//		computer = new Computer.ComputerBuilder().setName(name).setIntroduced(introduced)
//				.setDiscontinued(discontinued).setCompany(company).build();
//
//		return Optional.ofNullable(computer);
//	}
//}

//	}
//		AddComputerDto addComputerDto = new AddComputerDto.AddComputerDtoBuilder().build();
//		String companyId = null;
//		String companyName = null;
//		if (company != null) {
//			if (company.getId() != null) {
//				companyId = company.getId().toString();
//			}
//			if (company.getName() != null) {
//				companyName = company.getName();
//			}
//			cmpnDto = new String(companyId, companyName);
//		}
//		return cmpnDto;
//	}
//
//	public static List<String> companyToListCompanyDto(List<Optional<Company>> listOC) {
//		List<String> list = new ArrayList<String>();
//
//		for (Optional<Company> c : listOC) {
//			Company company = c.orElse(null);
//			if (company != null) {
//				list.add(companyToCompanyDto(company));
//			}
//		}
//
//		return list;
//	}
//
////	public static Company companyDtoToCompany(String companyDto) {
////		Company company = new Company();
////		Long id = null;
////		String name = null;
////
////		if (companyDto.getId() != null) {
////		id = Long.parseLong(companyDto.getId());
////		}
////		name = companyDto.getName();
////
////		company = new Company(id, name);
////		return company;
////	}
////}
