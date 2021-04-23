package com.excilys.formation.apis;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Page;
import com.excilys.formation.service.CompanyService;

@RestController
public class CompanyAPIs{
	@Autowired
	Page page;
	@Autowired
	CompanyService companyService;
	
	@GetMapping(value = "/companies", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Company> dashboardGet() {

		List<Company> listComputer = new ArrayList<Company>();
		listComputer = companyService.listCompaniesService().stream().map(x -> x.get()).collect(Collectors.toList());

		return listComputer;
	}
}
