package com.excilys.formation.servlets;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.Dto.AddComputerDto;
import com.excilys.formation.Dto.CompanyDto;
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
	private static final String NAME_MODEL_ATTTRIBUTE = "cmptDto";

	@Autowired
	ComputerService computerService;
	@Autowired
	CompanyService companyService;
	@Autowired
	ValidationComputer validationComputer;
	@Autowired
	Dashboard dashboard;
	
	Computer computer;

	@GetMapping("/editComputer")
	public ModelAndView getEditComputer(@RequestParam(required = false) String computerId) {

		ModelAndView modelAndView = new ModelAndView("editComputer");
		List<CompanyDto> listCmpnDto = new ArrayList<CompanyDto>();

		modelAndView.addObject(NAME_MODEL_ATTTRIBUTE, new AddComputerDto());
		listCmpnDto = MapperCompanyDto.listOptionalCompanyToListCompanyDto(companyService.listCompaniesService());
		modelAndView.addObject(LIST_COMPANY, listCmpnDto);

		String id = computerId;
		Long idComputer = Long.valueOf(id);
		Computer computerMemory = computerService.showComputerDetailsIdService(idComputer).orElse(null);
		modelAndView.addObject(LOADED_COMPUTER, computerMemory);

		return modelAndView;
	}

	@PostMapping("/editComputer")
	public ModelAndView postEditComputer(@ModelAttribute(NAME_MODEL_ATTTRIBUTE) AddComputerDto cmptDto, BindingResult br) {

	

		validationComputer.validate(cmptDto, br);
		if (br.hasErrors()) {
			return new ModelAndView("redirect:/editComputer");
		} else {
			computer = MapperComputerDto.addComputerDtoToComputer(cmptDto).orElse(computer);
			computerService.updateComputerService(computer.getId(), computer);
			return new ModelAndView("redirect:/dashboard");

		}

	}
}
