package com.excilys.formation.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.DAO.DAOComputer;
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
			Long company_id = null;

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
				company_id = result.getLong("company_id");
			}

			listeComputer.add(new Computer(id, name, introduced, discontinued, company_id));
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeComputer;
	}

}
