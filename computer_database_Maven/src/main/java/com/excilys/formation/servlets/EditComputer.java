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
import org.springframework.web.servlet.view.RedirectView;

import com.excilys.formation.Dto.AddComputerDto;
import com.excilys.formation.Dto.CompanyDto;
import com.excilys.formation.exceptions.InputException;
import com.excilys.formation.mapper.MapperCompanyDto;
import com.excilys.formation.mapper.MapperComputerDto;
import com.excilys.formation.model.Computer;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.validation.ValidationComputer;

/**
 * Servlet implementation class EditServlet
 */

@Controller
public class EditComputer {
	private static final String LIST_COMPANY = "listCompany";
	private static final String LOADED_COMPUTER = "loadedComputer";
	private static Logger LOGGER = LoggerFactory.getLogger(AddComputer.class);

	@Autowired
	ComputerService computerService;
	@Autowired
	CompanyService companyService;
	Computer computer;
	AddComputerDto cmptDto;
	@Autowired
	ValidationComputer verif;
	@Autowired
	Dashboard dashboard;

	@GetMapping("/editComputer")
	public ModelAndView getEditComputer(@RequestParam(required = false) String computerId) {

		ModelAndView modelAndView = new ModelAndView("editComputer");
		List<CompanyDto> listCmpnDto = new ArrayList<CompanyDto>();

		listCmpnDto = MapperCompanyDto.listOptionalCompanyToListCompanyDto(companyService.listCompaniesService());
		modelAndView.addObject(LIST_COMPANY, listCmpnDto);

		String id = computerId;
		Long idComputer = Long.valueOf(id);
		Computer computerMemory = computerService.showComputerDetailsIdService(idComputer).orElse(null);
		modelAndView.addObject(LOADED_COMPUTER, computerMemory);

		return modelAndView;
	}

	@PostMapping("/editComputer")
	public RedirectView postEditComputer(@RequestParam(required = false) String computerName,
			@RequestParam(required = false) String introduced, @RequestParam(required = false) String discontinued,
			@RequestParam(required = false) String companyId, @RequestParam(required = false) String id) {

		String computerName2 = computerName;
		String introduced2 = introduced;
		String discontinued2 = discontinued;
		String companyId2 = companyId;
		String idLoaded = id;
		System.out.println(idLoaded);

		cmptDto = new AddComputerDto.AddComputerDtoBuilder().setId(idLoaded).setName(computerName2)
				.setIntroduced(introduced2).setDiscontinued(discontinued2).setCompanyId(companyId2).build();
		computer = MapperComputerDto.addComputerDtoToComputer(cmptDto).orElse(computer);
		System.out.println(computer);

		if (verif.computerValidation(computer)) {
			computerService.updateComputerService(computer.getId(), computer); 
			return new RedirectView("dashboard");
		} else {
			LOGGER.error("il y a une erreur", new InputException("ko"));

			return new RedirectView("editComputer");
		}

	}
}
