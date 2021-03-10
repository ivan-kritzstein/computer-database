package com.excilys.formation.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOComputer extends DAO<Computer> {

	public DAOComputer(Connection conn) {
		super(conn);
	}

	public void create(Computer computer) {
		String request ="INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)";

		PreparedStatement preparedSelect;
		try {
			preparedSelect = connect.prepareStatement(request);
			preparedSelect.setString(1, computer.getName());
			preparedSelect.setDate(2, computer.getIntroduced());
			preparedSelect.setDate(3, computer.getDiscontinued());
			preparedSelect.setInt(4, computer.getCompany_id());
			preparedSelect.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	public void delete(int id) {
		String request ="delete computer where id=?";

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

	public void updateById(int id, Computer computer) {
		String request = "UPDATE computer SET id = ?, name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
		
		PreparedStatement preparedUpdate;
		
		try {
			preparedUpdate = connect.prepareStatement(request);
			preparedUpdate.setInt(1, computer.getId());
			preparedUpdate.setString(2, computer.getName());
			preparedUpdate.setDate(3, computer.getIntroduced());
			preparedUpdate.setDate(4, computer.getDiscontinued());
			preparedUpdate.setInt(5, computer.getCompany_id());
			preparedUpdate.setInt(6,id);
			preparedUpdate.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void updateByName(String name, Computer computer) {
		String request = "UPDATE computer SET id = ?, name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE name = ?" ;
		
		PreparedStatement preparedUpdate;
		
		try {
			preparedUpdate = connect.prepareStatement(request);
			preparedUpdate.setInt(1, computer.getId());
			preparedUpdate.setString(2, computer.getName());
			preparedUpdate.setDate(3, computer.getIntroduced());
			preparedUpdate.setDate(4, computer.getDiscontinued());
			preparedUpdate.setInt(5, computer.getCompany_id());
			preparedUpdate.setString(6, name);
			preparedUpdate.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public Computer showDetailsWithId(int id) {
		Computer computer = new Computer();      

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer WHERE id  = " + id);
			if(result.first())
				computer = new Computer(
						id,
						result.getString("name"),
						result.getDate("introduced"),
						result.getDate("discontinued"),
						result.getInt("company_id"));         
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	public Computer showDetailsWithName(String name) {
		Computer computer = new Computer();      

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer WHERE name  = " + name);
			if(result.first())
				computer = new Computer(

						result.getInt("id"),
						name,
						result.getDate("introduced"),
						result.getDate("discontinued"),
						result.getInt("company_id"));         
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

	public List<Computer> list (){
		List <Computer> listeComputer = new ArrayList<Computer>();    

		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM computer");
			while(result.next())
				listeComputer.add( new Computer(
						result.getInt("id"),
						result.getString("name"),
						result.getDate("introduced"),
						result.getDate("discontinued"),
						result.getInt("company_id")));         
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeComputer;
	}

}
