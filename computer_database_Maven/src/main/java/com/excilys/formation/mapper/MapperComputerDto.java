package com.excilys.formation.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.formation.Dto.ComputerDto;
import com.excilys.formation.model.Computer;

public class MapperComputerDto {

	public static ComputerDto computerToComputerDto(Computer computer) {
		ComputerDto cmptDto = new ComputerDto.ComputerDtoBuilder().build();
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
		String companyName = computer.getCompany().getName();

		cmptDto = new ComputerDto.ComputerDtoBuilder().setId(id).setName(name).setIntroduced(introduced)
				.setDiscontinued(discontinued).setCompanyName(companyName).build();

		return cmptDto;
	}
	
	public static List<ComputerDto> computerToListComputerDto (List<Optional<Computer>> listOC) {
		List<ComputerDto> list = new ArrayList<ComputerDto>();
		
		for(Optional<Computer> c : listOC) {
			Computer computer = c.orElse(null);
			if (computer != null) {
				list.add(computerToComputerDto(computer));
			}
		}
		
		return list;
		
	}
	
}
