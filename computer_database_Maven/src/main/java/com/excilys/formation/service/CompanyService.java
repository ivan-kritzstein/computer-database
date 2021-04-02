package com.excilys.formation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.DAO.DAOCompany;
import com.excilys.formation.model.Company;
@Component
public class CompanyService {
	@Autowired
	DAOCompany daocompany;
	
	public List<Optional<Company>> listCompaniesService() {

		return daocompany.list();
	}
	
	public Optional<Company> showCompanyDetailsIdService(Long idDetails) {

		return daocompany.showDetailsWithId(idDetails);
	}
	
	public Optional<Company> showCompanyDetailsNameSerive(String nameDetails) {

		return daocompany.showDetailsWithName(nameDetails);
	}
	
	public void deleteCompanyAndComputersAssociatedService(Long idDelate) {
		daocompany.delete(idDelate);
	}
}
