package com.excilys.formation.servlets;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.Dto.AddComputerDto;
import com.excilys.formation.Dto.CompanyDto;
import com.excilys.formation.mapper.MapperCompanyDto;
import com.excilys.formation.mapper.MapperComputerDto;
import com.excilys.formation.model.Computer;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.validation.ValidationComputer;

@Controller
public class AddComputer {

	private static final String LIST_COMPANY = "listCompany";
	private static final String NAME_MODEL_ATTTRIBUTE = "cmptDto";
	@Autowired
	ComputerService computerService;
	@Autowired
	CompanyService companyService;
	@Autowired
	ValidationComputer validationComputer;


	@GetMapping("/addComputer")
	public ModelAndView getAddComputer() {

		List<CompanyDto> listCmpnDto = new ArrayList<CompanyDto>();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(NAME_MODEL_ATTTRIBUTE, new AddComputerDto());
		listCmpnDto = MapperCompanyDto.listOptionalCompanyToListCompanyDto(companyService.listCompaniesService());
		modelAndView.addObject(LIST_COMPANY, listCmpnDto);

		return modelAndView;
	}

	@PostMapping("/addComputer")
	public ModelAndView postAddComputer(@ModelAttribute(NAME_MODEL_ATTTRIBUTE) AddComputerDto cmptDto, BindingResult br) {

		validationComputer.validate(cmptDto, br);
		if (br.hasErrors()) {
			return getAddComputer();
		} else {
			Optional<Computer> computer = MapperComputerDto.addComputerDtoToComputer(cmptDto);
			computerService.createComputerService(computer.get());
		}

		return getAddComputer();
	}

}
