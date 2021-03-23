package com.excilys.formation.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.mapper.MapperComputer;

public class Data {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/computer-database-db";
	private static final String USER = "admincdb";
	private static final String PASSWORD = "qwerty1234";
	private static Data data;
	private Connection con;
	private static Logger LOGGER = LoggerFactory.getLogger(Data.class);

	private Data() {

	}

	public static Data getInstance() {
		if (data == null) {
			data = new Data();
		}
		return data;
	}

	public Connection getConnection() { 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
		// use con here
		return con;
	}
// 1 cstr rivate pour 1 sel objet 
	// methode getInstance pour acceder a l'objet
	// attribut de l'objet data
}
