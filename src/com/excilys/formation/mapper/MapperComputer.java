package com.excilys.formation.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class MapperComputer {

	public List<Computer> dataSqlToComputer(ResultSet result) {
		
		List<Computer> listeComputer = new ArrayList<Computer>();
		try {
		while (result.next()) {
			Long id = null;
			String name = null;
			LocalDate introduced = null;
			LocalDate discontinued = null;
			Company company = null;

			if (result.getLong("id") != 0) {
				id = result.getLong("id");
			}
			if (result.getString("name") != null) {
				name = result.getString("name");
			}
			if (result.getDate("introduced") != null) {
				introduced = result.getDate("introduced").toLocalDate();
			}
			if (result.getDate("discontinued") != null) {
				discontinued = result.getDate("discontinued").toLocalDate();
			}
			if (result.getLong("company_id") != 0) {
				company = new Company();
				company.setId(result.getLong("company_id"));
			}

			listeComputer.add(new Computer(id, name, introduced, discontinued, company));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeComputer;
	}
// extraire le mapper pour un computer et passer ce mapper dans une boucle pour la liste
}
