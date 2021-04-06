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

import com.excilys.formation.mapper.MapperComputer;
import com.excilys.formation.model.Computer;
import com.excilys.formation.view.Page; 

@Repository
public class DAOComputer {

	protected DataSource connect;
	MapperComputer mapComputer;
	private static final String REQUEST_CREATE = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)";
	private static final String REQUEST_DELETE = "delete FROM computer where id=?";
	private static final String REQUEST_UPDATE_BY_ID = "UPDATE computer SET id = ?, name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private static final String REQUEST_UPDATE_BY_NAME = "UPDATE computer SET id = ?, name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE name = ?";
	private static final String REQUEST_DETAILS_WHITH_ID = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.id, company.name FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.id = ";
	private static final String REQUEST_DETAILS_WHITH_NAME = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.id, company.name FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.name LIKE ? ";
	private static final String NBR_COMPUTER = "SELECT COUNT(id) FROM computer WHERE computer.name LIKE ?";
	private static final String REQUEST_LIST_LIKE = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.id, company.name FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.name LIKE ?";
	private static final String REQUEST_LIMIT_OFFSET = " LIMIT ? OFFSET ?";

	private static final Logger LOGGER = LoggerFactory.getLogger(DAOComputer.class);

	@Autowired
	public DAOComputer(DataSource connect, MapperComputer mapperComputer) {
		this.connect = connect;
		mapComputer = mapperComputer;
	}

	public void create(Computer computer) {

		PreparedStatement preparedSelect;
		try (Connection con = connect.getConnection()) {
			preparedSelect = con.prepareStatement(REQUEST_CREATE);
			preparedSelect.setString(1, computer.getName());
			if (computer.getIntroduced() != null) {
				preparedSelect.setDate(2, java.sql.Date.valueOf(computer.getIntroduced()));
			} else {
				preparedSelect.setDate(2, null);
			}
			if (computer.getDiscontinued() != null) {
				preparedSelect.setDate(3, java.sql.Date.valueOf(computer.getDiscontinued()));
			} else {
				preparedSelect.setDate(3, null);
			}
			if (computer.getCompany().getId() != null && computer.getCompany().getId() != 0) {
				preparedSelect.setObject(4, computer.getCompany().getId());
			} else {
				preparedSelect.setObject(4, null);
			}
			preparedSelect.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}

	}

	public void delete(Long id) {

		PreparedStatement preparedDelete;
		try (Connection con = connect.getConnection()) {
			preparedDelete = con.prepareStatement(REQUEST_DELETE);
			if (id != null && id != 0) {
			preparedDelete.setLong(1, id);
			}
			preparedDelete.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
	}

	public void updateById(Long id, Computer computer) {
		PreparedStatement preparedUpdate;

		try (Connection con = connect.getConnection()) {
			preparedUpdate = con.prepareStatement(REQUEST_UPDATE_BY_ID);
			preparedUpdate.setLong(1, computer.getId());
			preparedUpdate.setString(2, computer.getName());
			if (computer.getIntroduced() != null) {
				preparedUpdate.setDate(3, java.sql.Date.valueOf(computer.getIntroduced()));
			} else {
				preparedUpdate.setDate(3, null);
			}
			if (computer.getDiscontinued() != null) {
				preparedUpdate.setDate(4, java.sql.Date.valueOf(computer.getDiscontinued()));
			} else {
				preparedUpdate.setDate(4, null);
			}
			if (computer.getCompany().getId() != null && computer.getCompany().getId() != 0) {
				preparedUpdate.setObject(5, computer.getCompany().getId());
			} else {
				preparedUpdate.setObject(5, null);
			}
			preparedUpdate.setLong(6, id);
			preparedUpdate.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
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
			LOGGER.error(e.getMessage());
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
			LOGGER.error(e.getMessage());
		}
		return computer;
	}

	public Optional<Computer> showDetailsWithName(String name) {

		Optional<Computer> computer = Optional.ofNullable(new Computer.ComputerBuilder().build());

		try (Connection con = connect.getConnection()) {
			Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet result = statement.executeQuery(REQUEST_DETAILS_WHITH_NAME + "'" + name + "'");
			if (result.first()) {
				computer = mapComputer.dataSqlToComputer(result);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return computer;
	}

	public List<Optional<Computer>> list(Page page) {
		List<Optional<Computer>> listeComputer = new ArrayList<Optional<Computer>>();

		try (Connection con = connect.getConnection();
				PreparedStatement prepareList = con.prepareStatement(
						REQUEST_LIST_LIKE + " ORDER BY " + page.getOrderBy() + REQUEST_LIMIT_OFFSET)) {
			prepareList.setString(1, "%" + page.getSearchComputer() + "%");
			prepareList.setInt(2, page.getLimit());
			prepareList.setInt(3, page.getOffset());
			ResultSet result = prepareList.executeQuery();
			listeComputer = mapComputer.dataSqlToListComputer(result);
			page.setNbrComputer(sizeListComputer(page));

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return listeComputer;
	}

	public int sizeListComputer(Page page) {
		try (Connection con = connect.getConnection();
				PreparedStatement prepareList = con.prepareStatement(NBR_COMPUTER)) {
			prepareList.setString(1, "%" + page.getSearchComputer() + "%");
			ResultSet result = prepareList.executeQuery();
			result.next();
			return result.getInt("count(id)");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
		return -1;
	}

}
