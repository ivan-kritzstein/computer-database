package com.excilys.formation.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.model.Company;

public class MapperCompany {

	public List<Company> dataSqlToCompany(ResultSet result) {
		List<Company> listeCompany = new ArrayList<Company>();
		try {
			while (result.next()) {
				listeCompany.add(new Company(result.getLong("id"), result.getString("name")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeCompany;
	}
}
