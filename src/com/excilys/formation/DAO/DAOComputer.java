package com.excilys.formation.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.mapper.Mapper;

import com.excilys.formation.mapper.MapperComputer;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;

public class DAOComputer extends DAO<Computer> {

	MapperComputer mapComputer;

	public DAOComputer(Connection conn) {
		super(conn);
		mapComputer = new MapperComputer();
	}

	public void create(Computer computer) {
		String request = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)";

		PreparedStatement preparedSelect;
		try {
			preparedSelect = connect.prepareStatement(request);
			preparedSelect.setString(1, computer.getName());
			preparedSelect.setDate(2, java.sql.Date.valueOf(computer.getIntroduced()));
			preparedSelect.setDate(3, java.sql.Date.valueOf(computer.getDiscontinued()));
			preparedSelect.setObject(4, computer.getCompany().getId());
			preparedSelect.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(Long id) {
		String request = "delete computer where id=?";

		PreparedStatement preparedDelete;
		try {
			preparedDelete = connect.prepareStatement(request);
			preparedDelete.setLong(1, id);
			preparedDelete.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateById(Long id, Computer computer) {
		String request = "UPDATE computer SET id = ?, name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";

		PreparedStatement preparedUpdate;

		try {
			preparedUpdate = connect.prepareStatement(request);
			preparedUpdate.setLong(1, computer.getId());
			preparedUpdate.setString(2, computer.getName());
			preparedUpdate.setDate(3, java.sql.Date.valueOf(computer.getIntroduced()));
			preparedUpdate.setDate(4, java.sql.Date.valueOf(computer.getDiscontinued()));
			preparedUpdate.setObject(5, computer.getCompany().getId());
			preparedUpdate.setLong(6, id);
			preparedUpdate.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateByName(String name, Computer computer) {
		String request = "UPDATE computer SET id = ?, name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE name = ?";

		PreparedStatement preparedUpdate;

		try {
			preparedUpdate = connect.prepareStatement(request);
			preparedUpdate.setLong(1, computer.getId());
			preparedUpdate.setString(2, computer.getName());
			preparedUpdate.setDate(3, java.sql.Date.valueOf(computer.getIntroduced()));
			preparedUpdate.setDate(4, java.sql.Date.valueOf(computer.getDiscontinued()));
			preparedUpdate.setObject(5, computer.getCompany().getId());
			preparedUpdate.setString(6, name);
			preparedUpdate.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Computer showDetailsWithId(Long id) {
		Computer computer = new Computer();
		Company company = new Company();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT computer.id, computer.name, introduced, discontinued, company.id FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE company.id  = "
									+ id);
			if (result.first()) {
				company.setId(result.getLong("company.id"));
				computer = new Computer(id, result.getString("computer.name"), result.getDate("introduced").toLocalDate(),
						result.getDate("discontinued").toLocalDate(), company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	public Computer showDetailsWithName(String name) { // NPE date, changer requete egalement voir mapper computer
		Computer computer = new Computer();
		Company company = new Company();

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT computer.id, computer.name, introduced, discontinued, company.id FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE company.name  = " + name);
			if (result.first()) {
				company.setId(result.getLong("company.id"));
				computer = new Computer(

						result.getLong("id"), name, result.getDate("introduced").toLocalDate(),
						result.getDate("discontinued").toLocalDate(), company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	public List<Computer> list() {
		ResultSet result = null;
		List<Computer> listeComputer = new ArrayList<Computer>();
		try {
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM computer");
			listeComputer = mapComputer.dataSqlToComputer(result);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeComputer;
	}

}
