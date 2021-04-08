package com.excilys.formation.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.mapper.CompanyRowMapper;
import com.excilys.formation.mapper.MapperCompany;
import com.excilys.formation.model.Company;

@Repository
public class DAOCompany {

	private static final String REQUEST_DELETE = "delete from company WHERE id = :id";
	private static final String REQUEST_SHOW_DETAILS_WITH_ID = "SELECT * FROM company WHERE id  = ";
	private static final String REQUEST_SHOW_DETAILS_WITH_NAME = "SELECT * FROM company WHERE name  = ";
	private static final String REQUEST_LIST = "SELECT company.id, company.name FROM company";
	private static final String REQUEST_DELETE_COMPUTER_OF_COMPANY = "delete FROM computer WHERE computer.company_id = :id";
	private static Logger LOGGER = LoggerFactory.getLogger(DAOCompany.class);

	protected DataSource connect;
	MapperCompany mapCompany;
	CompanyRowMapper companyRowMapper;
	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public DAOCompany(DataSource connect, MapperCompany mapperCompany, CompanyRowMapper companyRowMapper,
			JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

		this.connect = connect;
		this.mapCompany = mapperCompany;
		this.companyRowMapper = companyRowMapper;
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	
	@Transactional() 
	public void delete(Long id) {
		
		
		deleteComputerOfCompany(id);
		SqlParameterSource namedParameters = new MapSqlParameterSource()
		.addValue("id", id);
		namedParameterJdbcTemplate.update(REQUEST_DELETE, namedParameters);

	}

	
	public void deleteComputerOfCompany(Long id) {

		try {
			SqlParameterSource namedParameters = new MapSqlParameterSource()
					.addValue("id", id != null && id != 0 ? id : 0);
			namedParameterJdbcTemplate.update(REQUEST_DELETE_COMPUTER_OF_COMPANY, namedParameters);
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage());
		}
	}



	public Optional<Company> showDetailsWithId(Long id) {
		
		Company company = new Company();

		try {
			company = jdbcTemplate.queryForObject(REQUEST_SHOW_DETAILS_WITH_ID + id, companyRowMapper);
		} catch (DataAccessException e1) {
			// TODO Auto-generated catch block
			LOGGER.error(e1.getMessage());
		}		
		return Optional.ofNullable(company);
	}

	public Optional<Company> showDetailsWithName(String name) {
		Company company = new Company();

		try {
			company = jdbcTemplate.queryForObject(REQUEST_SHOW_DETAILS_WITH_NAME + "'" + name + "'", companyRowMapper);
		} catch (DataAccessException e1) {
			// TODO Auto-generated catch block
			LOGGER.error(e1.getMessage());
		}		
		return Optional.ofNullable(company);
	}

	public List<Optional<Company>> list() {
		List<Optional<Company>> listeOptionalCompany = new ArrayList<Optional<Company>>();
		List<Company> listeCompany = new ArrayList<Company>();

		try {
			listeCompany = jdbcTemplate.query(REQUEST_LIST, companyRowMapper);
			listeOptionalCompany = listeCompany.stream().map(x -> Optional.ofNullable(x)).collect(Collectors.toList());
		} catch (DataAccessException e1) {
			// TODO Auto-generated catch block
			LOGGER.error(e1.getMessage());
		}

		return listeOptionalCompany;
	}
}
