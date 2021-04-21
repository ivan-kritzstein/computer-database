package com.excilys.formation.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.formation.model.Company;

@Component
public class CompanyRowMapper implements RowMapper <Company> {

	public Company mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Company company = new Company(resultSet.getLong("company.id"), resultSet.getString("company.name"));

		return company;
	}
}
