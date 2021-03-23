package com.excilys.formation.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.mapper.MapperCompany;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Data;
import com.excilys.formation.servlets.AddComputerServlet;

public class DAOCompany {

	protected Data connect = Data.getInstance();
	MapperCompany mapCompany = new MapperCompany();
	private static Logger LOGGER = LoggerFactory.getLogger(DAOCompany.class);

	public DAOCompany(Connection conn) {
	}

	public void create(Company company) {

		String request = "INSERT INTO company (name) VALUES (?)";

		PreparedStatement preparedSelect;
		try (Connection con = connect.getConnection()) {
			preparedSelect = con.prepareStatement(request);
			preparedSelect.setString(1, company.getName());
			preparedSelect.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}

	}

	public void delete(Long id) {
		String request = "delete from company where id = ?";

		PreparedStatement preparedDelete;
		try (Connection con = connect.getConnection()) {
			preparedDelete = con.prepareStatement(request);
			preparedDelete.setLong(1, id);
			preparedDelete.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
	}

	public void updateById(Long id, Company company) {
		String request = "UPDATE company SET id = ?, name = ? WHERE id = " + id;

		PreparedStatement preparedUpdate;

		try (Connection con = connect.getConnection()) {
			preparedUpdate = con.prepareStatement(request);
			preparedUpdate.setLong(1, company.getId());
			preparedUpdate.setString(2, company.getName());
			preparedUpdate.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}

	}

	public void updateByName(String name, Company company) {
		String request = "UPDATE company SET id = ?, name = ? WHERE name = '" + name + "'";

		PreparedStatement preparedUpdate;

		try (Connection con = connect.getConnection()) {
			preparedUpdate = con.prepareStatement(request);
			preparedUpdate.setLong(1, company.getId());
			preparedUpdate.setString(2, company.getName());
			System.out.println(preparedUpdate.toString());
			preparedUpdate.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
	}

	public Optional<Company> showDetailsWithId(Long id) {
		Optional<Company> company = Optional.ofNullable(new Company());

		try (Connection con = connect.getConnection()) {
			Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet result = statement.executeQuery("SELECT * FROM company WHERE id  = " + id);
			if (result.first()) {
				company = mapCompany.dataSqlToCompany(result);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return company;
	}

	public Optional<Company> showDetailsWithName(String name) {
		Optional<Company> company = Optional.ofNullable(new Company());

		try (Connection con = connect.getConnection()) {
			Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet result = statement.executeQuery("SELECT * FROM company WHERE name  = '" + name + "'");
			if (result.first()) {
				company = mapCompany.dataSqlToCompany(result);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return company;
	}

	public List<Optional<Company>> list() {
		List<Optional<Company>> listeCompany = new ArrayList<Optional<Company>>();

		try (Connection con = connect.getConnection()) {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM company");
			listeCompany = mapCompany.dataSqlToListCompany(result);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return listeCompany;
	}

}
