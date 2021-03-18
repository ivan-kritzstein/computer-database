package com.excilys.formation.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class MapperComputer {

	public Optional<Computer> dataSqlToComputer(ResultSet result) {

		Computer computer = new Computer.ComputerBuilder().build();
		Company company = new Company();
		LocalDate introduced = null;
		LocalDate discontinued = null;

		try {

				Long id = result.getLong("id");

				String name = result.getString("name");

			if (result.getDate("introduced") != null) {
				introduced = result.getDate("introduced").toLocalDate();
			}
			if (result.getDate("discontinued") != null) {
				discontinued = result.getDate("discontinued").toLocalDate();
			}
				company.setId(result.getLong("company_id"));

			computer = new Computer.ComputerBuilder().setId(id).setName(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(company).build();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(computer);
	}


	public List<Optional<Computer>> dataSqlToListComputer(ResultSet result) {
		List<Optional<Computer>> listeComputer = new ArrayList<Optional<Computer>>();
		try {
			while (result.next()) {

				listeComputer.add(dataSqlToComputer(result));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listeComputer;
	}

}
