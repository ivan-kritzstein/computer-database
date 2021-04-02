package com.excilys.formation.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.excilys.formation.model.Company;
@Component
public class MapperCompany {
	private static Logger LOGGER = LoggerFactory.getLogger(MapperCompany.class);

	public static Optional<Company> dataSqlToCompany(ResultSet result) {
		Optional<Company> company = Optional.empty();
		try {
//			if (result.getString("name") != null) {
//				name = result.getString("name");
//			}
			company = Optional.ofNullable(new Company(result.getLong("company.id"), result.getString("company.name")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
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
			LOGGER.error(e.getMessage());
		}
		return listeCompany;
	}

}
