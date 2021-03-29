package com.excilys.formation.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.mapper.MapperComputer;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class Data {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/computer-database-db";
	private static final String USER = "admincdb";
	private static final String PASSWORD = "qwerty1234";
	private static Data data;
	private static Connection con;
	private static Logger LOGGER = LoggerFactory.getLogger(Data.class);

	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;

	static {
		config.setJdbcUrl(URL);
		config.setUsername(USER);
		config.setPassword(PASSWORD);
		config.setDriverClassName("com.mysql.jdbc.Driver");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		ds = new HikariDataSource(config);
	}

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
			con = ds.getConnection();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		
		}
		return con;
	}


}
