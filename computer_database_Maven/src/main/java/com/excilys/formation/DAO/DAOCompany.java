package com.excilys.formation.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.mapper.MapperCompany;
import com.excilys.formation.model.Company;

@Repository
public class DAOCompany {

	protected DataSource connect;
	MapperCompany mapCompany;
	private static final String REQUEST_CREATE = "INSERT INTO company (name) VALUES (?)";
	private static final String REQUEST_DELETE = "delete from company WHERE id = ?";
	private static final String REQUEST_UPDTATE_ID = "UPDATE company SET id = ?, name = ? WHERE id = ?";
	private static final String REQUEST_UPDATE_NAME = "UPDATE company SET id = ?, name = ? WHERE name = '?'";
	private static final String REQUEST_LIST = "SELECT company.id, company.name FROM company";
	private static final String REQUEST_DELETE_COMPUTER_OF_COMPANY = "delete FROM computer WHERE computer.company_id = ?";
	private static Logger LOGGER = LoggerFactory.getLogger(DAOCompany.class);

	@Autowired
	public DAOCompany(DataSource connect, MapperCompany mapperCompany) {

		this.connect = connect;
		this.mapCompany = mapperCompany;
	}

	public void create(Company company) {

		PreparedStatement preparedSelect;
		try (Connection con = connect.getConnection()) {
			preparedSelect = con.prepareStatement(REQUEST_CREATE);
			preparedSelect.setString(1, company.getName());
			preparedSelect.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}

	}

	public void delete(Long id) {

		try (Connection con = connect.getConnection()) {

			PreparedStatement preparedDelete;
			try {
				con.setAutoCommit(false);
				deleteComputerOfCompany(id);
				preparedDelete = con.prepareStatement(REQUEST_DELETE);
				preparedDelete.setLong(1, id);
				preparedDelete.execute();
				con.commit();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage() + e.getStackTrace());
				// TODO Auto-generated catch block
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					LOGGER.error(e1.getMessage() + e1.getStackTrace());
				}
				;
				LOGGER.error(e.getMessage());
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					LOGGER.error(e.getMessage() + e.getStackTrace());
				}
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			LOGGER.error(e2.getMessage() + e2.getStackTrace());
		}
	}

	public void deleteComputerOfCompany(Long id) {

		PreparedStatement preparedDelete;
		try (Connection con = connect.getConnection()) {
			preparedDelete = con.prepareStatement(REQUEST_DELETE_COMPUTER_OF_COMPANY);
			if (id != null && id != 0) {
				preparedDelete.setLong(1, id);
			}
			preparedDelete.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
	}

	public void updateById(Long id, Company company) {

		PreparedStatement preparedUpdate;

		try (Connection con = connect.getConnection()) {
			preparedUpdate = con.prepareStatement(REQUEST_UPDTATE_ID);
			preparedUpdate.setLong(1, company.getId());
			preparedUpdate.setString(2, company.getName());
			preparedUpdate.setLong(3, id);
			preparedUpdate.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}

	}

	public void updateByName(String name, Company company) {

		PreparedStatement preparedUpdate;

		try (Connection con = connect.getConnection()) {
			preparedUpdate = con.prepareStatement(REQUEST_UPDATE_NAME);
			preparedUpdate.setLong(1, company.getId());
			preparedUpdate.setString(2, company.getName());
			preparedUpdate.setString(3, name);
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
				company = MapperCompany.dataSqlToCompany(result);
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
				company = MapperCompany.dataSqlToCompany(result);
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
			ResultSet result = statement.executeQuery(REQUEST_LIST);
			listeCompany = mapCompany.dataSqlToListCompany(result);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return listeCompany;
	}

}
