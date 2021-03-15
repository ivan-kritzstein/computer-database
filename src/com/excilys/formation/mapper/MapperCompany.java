package com.excilys.formation.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.model.Company;

public class MapperCompany {

	public Company dataSqlToCompany(ResultSet result) {
		Company company = new Company();
		String name = null;
		try {
			if (result.getString("name") != null) {
				name = result.getString("name");
			}
			company = new Company(result.getLong("id"), name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return company;
	}

	public List<Company> dataSqlToListCompany(ResultSet result) {
		List<Company> listeCompany = new ArrayList<Company>();
		try {
			while (result.next()) {
				listeCompany.add(dataSqlToCompany(result));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeCompany;
	}

}
