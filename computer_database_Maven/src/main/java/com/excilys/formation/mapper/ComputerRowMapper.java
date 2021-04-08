package com.excilys.formation.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

@Component
public class ComputerRowMapper implements RowMapper<Computer> {

	public Computer mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		Computer computer;
		Company company = new Company();
		LocalDate introduced = null;
		LocalDate discontinued = null;

		Long id = resultSet.getLong("computer.id");
		String name = resultSet.getString("computer.name");

		if (resultSet.getDate("introduced") != null && resultSet.getString("introduced") != "null") {
			introduced = resultSet.getDate("computer.introduced").toLocalDate();
		}
		if (resultSet.getDate("discontinued") != null && resultSet.getString("discontinued") != "null") {
			discontinued = resultSet.getDate("discontinued").toLocalDate();
		}
		company = new Company(resultSet.getLong("company.id"), resultSet.getString("company.name"));

		computer = new Computer.ComputerBuilder().setId(id).setName(name).setIntroduced(introduced)
				.setDiscontinued(discontinued).setCompany(company).build();

		return computer;
	}

}
