package com.excilys.formation.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.formation.Dto.AddComputerDto;
import com.excilys.formation.Dto.CompanyHQLDto;
import com.excilys.formation.Dto.ComputerHQLDto;
import com.excilys.formation.Dto.ListComputerDto;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class MapperComputerDto {

	public static ListComputerDto computerToListComputerDto(Computer computer) {
		ListComputerDto cmptDto = new ListComputerDto.ListComputerDtoBuilder().build();

		String companyName = null;
		String introduced = null;
		String discontinued = null;

		String id = computer.getId().toString();
		String name = computer.getName();

		if (computer.getIntroduced() != null) {
			introduced = computer.getIntroduced().toString();
		}
		if (computer.getDiscontinued() != null) {
			discontinued = computer.getDiscontinued().toString();
		}

		if (computer.getCompany().getName() != null) {
			companyName = computer.getCompany().getName();
		}

		cmptDto = new ListComputerDto.ListComputerDtoBuilder().setId(id).setName(name).setIntroduced(introduced)
				.setDiscontinued(discontinued).setCompanyName(companyName).build();

		return cmptDto;
	}

	public static List<ListComputerDto> listOptionalComputerToListComputerDto(List<Optional<Computer>> listOC) {
		List<ListComputerDto> list = new ArrayList<ListComputerDto>();

		for (Optional<Computer> c : listOC) {
			Computer computer = c.orElse(null);
			if (computer != null) {
				list.add(computerToListComputerDto(computer));

			}
		}
		return list;

	}

	public static Optional<Computer> computerDtoToComputer(ListComputerDto cmptDto) {
		Computer computer = new Computer.ComputerBuilder().build();

		Long id = null;
		String name = null;
		LocalDate introduced = null;
		LocalDate discontinued = null;

		Company company = new Company();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		if (cmptDto.getId() != null) {
			id = Long.parseLong(cmptDto.getId());
		}

		if (null != cmptDto.getName()) {
			name = cmptDto.getName();
		}

		if (null != cmptDto.getIntroduced() && cmptDto.getIntroduced().compareTo("") != 0) {
			introduced = LocalDate.parse(cmptDto.getIntroduced(), df);
		}
		if (cmptDto.getDiscontinued() != null && cmptDto.getDiscontinued().compareTo("") != 0) {
			discontinued = LocalDate.parse(cmptDto.getDiscontinued(), df);
		}
		if (cmptDto.getCompanyName() != null) {
			company.setName(cmptDto.getCompanyName());
		}

		computer = new Computer.ComputerBuilder().setId(id).setName(name).setIntroduced(introduced)
				.setDiscontinued(discontinued).setCompany(company).build();

		return Optional.ofNullable(computer);
	}

	public static Optional<Computer> addComputerDtoToComputer(AddComputerDto cmptDto) {
		Computer computer = new Computer.ComputerBuilder().build();

		Long id = null;
		String name = null;
		LocalDate introduced = null;
		LocalDate discontinued = null;

		Company company = new Company();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		if (cmptDto.getId() != null) {
			id = Long.parseLong(cmptDto.getId());
		}

		if (null != cmptDto.getName()) {
			name = cmptDto.getName();
		}

		if (null != cmptDto.getIntroduced() && cmptDto.getIntroduced().compareTo("") != 0) {
			introduced = LocalDate.parse(cmptDto.getIntroduced(), df);
		}
		if (cmptDto.getDiscontinued() != null && cmptDto.getDiscontinued().compareTo("") != 0) {
			discontinued = LocalDate.parse(cmptDto.getDiscontinued(), df);
		}
		if (cmptDto.getCompany_id() != null) {
			company.setId(Long.parseLong(cmptDto.getCompany_id()));
		}

		computer = new Computer.ComputerBuilder().setId(id).setName(name).setIntroduced(introduced)
				.setDiscontinued(discontinued).setCompany(company).build();

		return Optional.ofNullable(computer);
	}

	public static Optional<Computer> computerHQLDtoToComputer(ComputerHQLDto computerHQLDto) {
		Computer computer = new Computer.ComputerBuilder().build();

		Long id = null;
		String name = null;
		LocalDate introduced = null;
		LocalDate discontinued = null;

		Company company = new Company();

		if (computerHQLDto.getId() != null) {
			id = computerHQLDto.getId();
		}

		if (null != computerHQLDto.getName()) {
			name = computerHQLDto.getName();
		}

		if (null != computerHQLDto.getIntroduced()) {
			introduced = computerHQLDto.getIntroduced();
		}
		if (computerHQLDto.getDiscontinued() != null) {
			discontinued = computerHQLDto.getDiscontinued();
		}
		if (computerHQLDto.getCompanyHQLDto() != null) {
			company.setId(computerHQLDto.getCompanyHQLDto().getId());
			company.setName(computerHQLDto.getCompanyHQLDto().getName());
		}

		computer = new Computer.ComputerBuilder().setId(id).setName(name).setIntroduced(introduced)
				.setDiscontinued(discontinued).setCompany(company).build();

		return Optional.ofNullable(computer);
	}

	public static ComputerHQLDto computerToComputerHQLDto(Computer computer) {
		ComputerHQLDto computerHQLDto = new ComputerHQLDto.ComputerHQLDtoBuilder().build();

		Long id = null;
		String name = null;
		LocalDate introduced = null;
		LocalDate discontinued = null;
		Company company = null;
		CompanyHQLDto companyHQLDto = new CompanyHQLDto();

		
		if (computer.getId() != null) {
			id = computer.getId();
		}

		if (null != computer.getName()) {
			name = computer.getName();
		}

		if (null != computer.getIntroduced()) {
			introduced = computer.getIntroduced();
		}
		if (computer.getDiscontinued() != null) {
			discontinued = computer.getDiscontinued();
		}
		if (computer.getCompany() != null) {
			companyHQLDto = MapperCompanyDto.companyToCompanyHQLDto(company) ;  
		}
		
		computerHQLDto = new ComputerHQLDto.ComputerHQLDtoBuilder().setId(id).setName(name).setIntroduced(introduced)
				.setDiscontinued(discontinued).setCompanyHQLDto(companyHQLDto).build();

		return computerHQLDto;
	}
	
	public static AddComputerDto computerToAddComputerDto(Computer computer) {

		String companyId = null;
		String introduced = null;
		String discontinued = null;
		AddComputerDto addcmptDto = new AddComputerDto();

		String id = computer.getId().toString();
		String name = computer.getName();

		if (computer.getIntroduced() != null) {
			introduced = computer.getIntroduced().toString();
		}
		if (computer.getDiscontinued() != null) {
			discontinued = computer.getDiscontinued().toString();
		}

		if (computer.getCompany().getId() != null) {
			companyId = computer.getCompany().getId().toString();
		}
		
		addcmptDto.setId(id);
		addcmptDto.setName(name);
		addcmptDto.setIntroduced(introduced);
		addcmptDto.setDiscontinued(discontinued);
		addcmptDto.setCompanyId(companyId);

		return addcmptDto;
	}
}
