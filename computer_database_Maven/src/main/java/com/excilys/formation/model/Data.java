package com.excilys.formation.model;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class Data {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/computer-database-db";
	private static final String USER = "admincdb";
	private static final String PASSWORD = "qwerty1234";
//	private static Data data;
	private Connection con;
	private Logger LOGGER = LoggerFactory.getLogger(Data.class);

	private HikariDataSource ds;

	public Data() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(URL);
		config.setUsername(USER);
		config.setPassword(PASSWORD);
		config.setDriverClassName("com.mysql.cj.jdbc.Driver"); 
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		ds = new HikariDataSource(config);
	}

//	public static Data getInstance() {
//		if (data == null) {
//			data = new Data();
//		}
//		return data;
//	}

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
