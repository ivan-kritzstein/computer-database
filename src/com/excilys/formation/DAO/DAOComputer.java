package com.excilys.formation.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.mapper.MapperComputer;
import com.excilys.formation.model.Computer;
import com.excilys.formation.model.Data;

public class DAOComputer { // extends DAO<Computer> {

	protected Data connect = Data.getInstance();
	MapperComputer mapComputer;

	public DAOComputer(Connection conn) {
		mapComputer = new MapperComputer();
	}

	public void create(Computer computer) {

		String request = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)";

		PreparedStatement preparedSelect;
		try (Connection con = connect.getConnection()) {
			preparedSelect = con.prepareStatement(request);
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
		try (Connection con = connect.getConnection()) {
			preparedDelete = con.prepareStatement(request);
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

		try (Connection con = connect.getConnection()) {
			preparedUpdate = con.prepareStatement(request);
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

		try (Connection con = connect.getConnection()) {
			preparedUpdate = con.prepareStatement(request);
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
		Computer computer = new Computer.ComputerBuilder().build();
		try (Connection con = connect.getConnection()) {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(
					"SELECT computer.id, computer.name, introduced, discontinued, company_id FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id  = "
							+ id);
			if (result.first()) {
				computer = mapComputer.dataSqlToComputer(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	public Computer showDetailsWithName(String name) { // NPE date, changer requete egalement voir mapper computer
		Computer computer = new Computer.ComputerBuilder().build();

		try (Connection con = connect.getConnection()) {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(
					"SELECT computer.id, computer.name, introduced, discontinued, company_id FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.name  = "
							+ "'" + name + "'");
			if (result.first()) {
				computer = mapComputer.dataSqlToComputer(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	public List<Computer> list() {
		List<Computer> listeComputer = new ArrayList<Computer>();
		try (Connection con = connect.getConnection()) {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM computer");
			listeComputer = mapComputer.dataSqlToListComputer(result);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeComputer;
	}

}
