package com.excilys.formation.controller;

import java.util.List;
import java.util.Optional;

import com.excilys.formation.model.Company;
import com.excilys.formation.service.CompanyService;

public class CompanyController {
	CompanyService companyService = new CompanyService(); 

	public List<Optional<Company>> listCompaniesController() {
		return companyService.listCompaniesService();
	}

	public Optional<Company> showCompanyDetailsIdController(Long idDetails) {
		return companyService.showCompanyDetailsIdService(idDetails);
	}

	public Optional<Company> showCompanyDetailsNameController(String nameDetails) {
		
		return companyService.showCompanyDetailsNameSerive(nameDetails);
	}

}
