package com.excilys.formation.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.DAO.DAOComputer;
import com.excilys.formation.Dto.CompanyDto;
import com.excilys.formation.Dto.ComputerDto;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class MapperComputerDto {
	private static final Logger LOGGER = LoggerFactory.getLogger(DAOComputer.class);

	public static ComputerDto computerToComputerDto(Computer computer) {
		ComputerDto cmptDto = new ComputerDto.ComputerDtoBuilder().build();
		
		String introduced = null;
		String discontinued = null;
		Company company = new Company();
		CompanyDto companyDto = new CompanyDto();
//		String companyName = null;

		String id = computer.getId().toString();
		String name = computer.getName();

		if (computer.getIntroduced() != null) {
			introduced = computer.getIntroduced().toString();
		}
		if (computer.getDiscontinued() != null) {
			discontinued = computer.getDiscontinued().toString();
		}

		company = computer.getCompany();
		companyDto = MapperCompanyDto.companyToCompanyDto(company);

		cmptDto = new ComputerDto.ComputerDtoBuilder().setId(id).setName(name).setIntroduced(introduced)
				.setDiscontinued(discontinued).setCompanyDto(companyDto).build();

		return cmptDto;
	}

	public static List<ComputerDto> listOptionalComputerToListComputerDto(List<Optional<Computer>> listOC) {
		List<ComputerDto> list = new ArrayList<ComputerDto>();

		for (Optional<Computer> c : listOC) {
			Computer computer = c.orElse(null);
			if (computer != null) {
				list.add(computerToComputerDto(computer));

			}
		}
		return list;

	}

	public static Optional<Computer> computerDtoToComputer(ComputerDto cmptDto) {
		Computer computer = new Computer.ComputerBuilder().build();

		Long id = null;
		String name = null;
		LocalDate introduced = null;
		LocalDate discontinued = null;
		CompanyDto companyDto = new CompanyDto();
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
		companyDto = cmptDto.getCompanyDto();
		company = MapperCompanyDto.companyDtoToCompany(companyDto);

		computer = new Computer.ComputerBuilder().setId(id).setName(name).setIntroduced(introduced)
				.setDiscontinued(discontinued).setCompany(company).build();

		return Optional.ofNullable(computer);
	}

}
