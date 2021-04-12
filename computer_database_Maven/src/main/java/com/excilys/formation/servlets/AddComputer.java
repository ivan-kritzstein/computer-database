package com.excilys.formation.servlets;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.Dto.AddComputerDto;
import com.excilys.formation.Dto.CompanyDto;
import com.excilys.formation.exceptions.InputException;
import com.excilys.formation.mapper.MapperCompanyDto;
import com.excilys.formation.mapper.MapperComputerDto;
import com.excilys.formation.model.Computer;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.validation.ValidationComputer;

@Controller
public class AddComputer {

	private static final String LIST_COMPANY = "listCompany";
	@Autowired
	ComputerService computerService;
	@Autowired
	CompanyService companyService;
	@Autowired
	ValidationComputer validationComputer;

	Computer computer;
	AddComputerDto cmptDto;

	private static Logger LOGGER = LoggerFactory.getLogger(AddComputer.class);

	@GetMapping("/addComputer")
	public ModelAndView getAddComputer() {

		List<CompanyDto> listCmpnDto = new ArrayList<CompanyDto>();
		ModelAndView modelAndView = new ModelAndView();

		listCmpnDto = MapperCompanyDto.listOptionalCompanyToListCompanyDto(companyService.listCompaniesService());
		modelAndView.addObject(LIST_COMPANY, listCmpnDto);

		return modelAndView;
	}

	@PostMapping("/addComputer")
	public ModelAndView postAddComputer(@RequestParam(required = false) String computerName,
			@RequestParam(required = false) String introduced, @RequestParam(required = false) String discontinued,
			@RequestParam(required = false) String companyId) {

		String computerName2 = computerName;
		String introduced2 = introduced;
		String discontinued2 = discontinued;
		String companyId2 = companyId;

		cmptDto = new AddComputerDto.AddComputerDtoBuilder().setName(computerName2).setIntroduced(introduced2)
				.setDiscontinued(discontinued2).setCompanyId(companyId2).build();
		computer = MapperComputerDto.addComputerDtoToComputer(cmptDto).orElse(computer);

		System.out.println(computer);
		if (validationComputer.computerValidation(computer)) {
			computerService.createComputerService(computer);
		} else {
			LOGGER.error("il y a une erreur", new InputException("ko"));
		}
		return getAddComputer();
	}

}
