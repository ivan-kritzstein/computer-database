package com.excilys.formation.service;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import com.excilys.formation.DAO.DAOCompany;
import com.excilys.formation.model.Company;

public class CompanyService {
	Connection con;

	public List<Optional<Company>> listCompaniesService() {
		DAOCompany daocp = new DAOCompany(con);
		return daocp.list();
	}
	
	public Optional<Company> showCompanyDetailsIdService(Long idDetails) {
		DAOCompany daocp = new DAOCompany(con);
		return daocp.showDetailsWithId(idDetails);
	}
	
	public Optional<Company> showCompanyDetailsNameSerive(String nameDetails) {
		DAOCompany daocp = new DAOCompany(con);
		return daocp.showDetailsWithName(nameDetails);
	}
}
