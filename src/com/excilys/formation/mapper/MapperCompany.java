package com.excilys.formation.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.formation.model.Company;

public class MapperCompany {

	public Optional<Company> dataSqlToCompany(ResultSet result) {
		Optional<Company> company = Optional.empty();
		try {
//			if (result.getString("name") != null) {
//				name = result.getString("name");
//			}
			company = Optional.ofNullable(new Company(result.getLong("id"), result.getString("name")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return company;
	}

	public List<Optional<Company>> dataSqlToListCompany(ResultSet result) {
		List<Optional<Company>> listeCompany = new ArrayList<Optional<Company>>();
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
