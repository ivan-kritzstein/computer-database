package com.excilys.formation.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.formation.mapper.MapperComputer;
import com.excilys.formation.model.Computer;
import com.excilys.formation.model.Data;

public class DAOComputer { // extends DAO<Computer> {

	protected Data connect = Data.getInstance();
	MapperComputer mapComputer;
	private static final String REQUEST_CREATE = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)"; 
	private static final String REQUEST_DELETE = "delete FROM computer where id=?"; 
	private static final String REQUEST_UPDATE_BY_ID = "UPDATE computer SET id = ?, name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?"; 
	private static final String REQUEST_UPDATE_BY_NAME = "UPDATE computer SET id = ?, name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE name = ?"; 
	private static final String REQUEST_DETAILS_WHITH_ID = "SELECT computer.id, computer.name, introduced, discontinued, company_id FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.id  = "; 
	private static final String REQUEST_DETAILS_WHITH_NAME = "SELECT computer.id, computer.name, introduced, discontinued, company_id FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.name  = "; 
	private static final String REQUEST_LIST = "SELECT id, name, introduced, discontinued, company_id FROM computer"; 

	
	public DAOComputer(Connection conn) {
		mapComputer = new MapperComputer();
	}

	public void create(Computer computer) {

		PreparedStatement preparedSelect;
		try (Connection con = connect.getConnection()) {
			preparedSelect = con.prepareStatement(REQUEST_CREATE);
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

		PreparedStatement preparedDelete;
		try (Connection con = connect.getConnection()) {
			preparedDelete = con.prepareStatement(REQUEST_DELETE);
			preparedDelete.setLong(1, id);
			preparedDelete.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateById(Long id, Computer computer) {
		PreparedStatement preparedUpdate;

		try (Connection con = connect.getConnection()) {
			preparedUpdate = con.prepareStatement(REQUEST_UPDATE_BY_ID);
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

		PreparedStatement preparedUpdate;

		try (Connection con = connect.getConnection()) {
			preparedUpdate = con.prepareStatement(REQUEST_UPDATE_BY_NAME);
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

	public Optional<Computer> showDetailsWithId(Long id) {
		Optional<Computer> computer = Optional.ofNullable(new Computer.ComputerBuilder().build());
		try (Connection con = connect.getConnection()) {
			Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet result = statement.executeQuery(REQUEST_DETAILS_WHITH_ID + id);
			if (result.first()) {
				computer = mapComputer.dataSqlToComputer(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	public Optional<Computer> showDetailsWithName(String name) { // NPE date, changer requete egalement voir mapper computer
		Optional<Computer> computer = Optional.ofNullable(new Computer.ComputerBuilder().build());

		try (Connection con = connect.getConnection()) {
			Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet result = statement.executeQuery(REQUEST_DETAILS_WHITH_NAME + "'" + name + "'");
			if (result.first()) {
				computer = mapComputer.dataSqlToComputer(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	public List<Optional<Computer>> list() {
		List<Optional<Computer>> listeComputer = new ArrayList<Optional<Computer>>();
		try (Connection con = connect.getConnection()) {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery(REQUEST_LIST);
			listeComputer = mapComputer.dataSqlToListComputer(result);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeComputer;
	}

}
