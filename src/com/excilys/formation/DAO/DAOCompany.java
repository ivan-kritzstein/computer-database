package com.excilys.formation.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.mapper.MapperCompany;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Data;

public class DAOCompany { // extends DAO<Company> {

	protected Data connect = Data.getInstance();
	MapperCompany mapCompany = new MapperCompany();

	public DAOCompany(Connection conn) {
//		super(conn);
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
		}
	}

	public Company showDetailsWithId(Long id) {
		Company company = new Company();

		try (Connection con = connect.getConnection()) {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM company WHERE id  = " + id);
			if (result.first()) {
				company = mapCompany.dataSqlToCompany(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

	public Company showDetailsWithName(String name) {
		Company company = null;

		try (Connection con = connect.getConnection()) {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM company WHERE name  = '" + name + "'");
			if (result.first()) {
				company = mapCompany.dataSqlToCompany(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

	public List<Company> list() {
		List<Company> listeCompany = new ArrayList<Company>();

		try (Connection con = connect.getConnection()) {
			Statement statement = con.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM company");
			listeCompany = mapCompany.dataSqlToListCompany(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeCompany;
	}

}
