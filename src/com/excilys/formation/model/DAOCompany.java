package com.excilys.formation.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCompany extends DAO<Company> {

	public DAOCompany(Connection conn) {
		super(conn);
	}

	public void create(Company company) {

		String request ="INSERT INTO company (name) VALUES (?)";



		PreparedStatement preparedSelect;
		try {
			preparedSelect = connect.prepareStatement(request);
			preparedSelect.setString(1, company.getName());
			preparedSelect.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void delete(int id) {
		String request ="delete from company where id = ?";

		PreparedStatement preparedDelete;
		try {
			preparedDelete = connect.prepareStatement(request);
			preparedDelete.setInt(1, id);
			preparedDelete.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateById(int id, Company company) {
		String request = "UPDATE company SET id = ?, name = ? WHERE id = " + id;

		PreparedStatement preparedUpdate;

		try {
			preparedUpdate = connect.prepareStatement(request);
			preparedUpdate.setInt(1, company.getId());
			preparedUpdate.setString(2, company.getName());
			preparedUpdate.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void updateByName(String name, Company company) {
		String request = "UPDATE company SET id = ?, name = ? WHERE name = '" + name+"'";

		PreparedStatement preparedUpdate;

		try {
			preparedUpdate = connect.prepareStatement(request);
			preparedUpdate.setInt(1, company.getId());
			preparedUpdate.setString(2, company.getName());
			System.out.println(preparedUpdate.toString());
			preparedUpdate.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Company showDetailsWithId(int id) {
		Company company = new Company();      

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM company WHERE id  = " + id);
			if(result.first())
				company = new Company(
						id,
						result.getString("name"));      
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

	public Company showDetailsWithName(String name) {
		Company company = null;      

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM company WHERE name  = '" + name+"'");
			if(result.first()) {
				company = new Company(
						result.getInt("id"),
						name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}

	public List<Company> list (){
		List <Company> listeCompany = new ArrayList<Company>();      

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM company");
			while(result.next())
				listeCompany.add( new Company(
						result.getInt("id"),
						result.getString("name")));         
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeCompany;
	}

}
