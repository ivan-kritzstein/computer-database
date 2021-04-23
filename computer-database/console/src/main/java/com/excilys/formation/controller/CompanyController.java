package com.excilys.formation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.excilys.formation.model.Company;
import com.excilys.formation.service.CompanyService;
@Controller
public class CompanyController {
	CompanyService companyService; 
	
	@Autowired
	public CompanyController (CompanyService companyService){
		this.companyService = companyService;
	}

	public List<Optional<Company>> listCompaniesController() {
		System.out.println(companyService);
		return companyService.listCompaniesService();
	}

	public Optional<Company> showCompanyDetailsIdController(Long idDetails) {
		return companyService.showCompanyDetailsIdService(idDetails);
	}

	public Optional<Company> showCompanyDetailsNameController(String nameDetails) {
		
		return companyService.showCompanyDetailsNameSerive(nameDetails);
	}
	
	public void deleteCompanyAndComputersAssociatedController(Long idDelate) {
		companyService.deleteCompanyAndComputersAssociatedService(idDelate);
	}
}