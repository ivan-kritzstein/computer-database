package com.excilys.formation.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
@Component
public class MapperComputer {

	private static Logger LOGGER = LoggerFactory.getLogger(MapperComputer.class);
	public Optional<Computer> dataSqlToComputer(ResultSet result) {

		Computer computer = new Computer.ComputerBuilder().build();
		Company company = new Company();
		LocalDate introduced = null;
		LocalDate discontinued = null;

		try {

				Long id = result.getLong("id");

				String name = result.getString("name");

			if (result.getDate("introduced") != null && result.getString("introduced") != "null") {
				introduced = result.getDate("introduced").toLocalDate();
			}
			if (result.getDate("discontinued") != null && result.getString("discontinued") != "null") {
				discontinued = result.getDate("discontinued").toLocalDate();
			}
				company = MapperCompany.dataSqlToCompany(result).orElse(null);

			computer = new Computer.ComputerBuilder().setId(id).setName(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(company).build();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
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
			LOGGER.error(e.getMessage());
		}

		return listeComputer;
	}

}
