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

import com.excilys.formation.mapper.ComputerRowMapper;
import com.excilys.formation.mapper.MapperComputer;
import com.excilys.formation.model.Computer;
import com.excilys.formation.view.Page;

@Repository
public class DAOComputer {

	private static final String REQUEST_CREATE = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (:name , :introduced , :discontinued, :companyId)";
	private static final String REQUEST_DELETE = "delete FROM computer where id= :id";
	private static final String REQUEST_UPDATE_BY_ID = "UPDATE computer SET id = :1 , name = :2 , introduced = :3 , discontinued = :4 , company_id = :5 WHERE id = :6";
	private static final String REQUEST_UPDATE_BY_NAME = "UPDATE computer SET id = :1 , name = :2 , introduced = :3 , discontinued = :4 , company_id = :5 WHERE name = :6";
	private static final String REQUEST_DETAILS_WHITH_ID = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.id, company.name FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.id = ";
	private static final String REQUEST_DETAILS_WHITH_NAME = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.id, company.name FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.name = ";
	private static final String NBR_COMPUTER = "SELECT COUNT(id) FROM computer WHERE computer.name LIKE :count";
	private static final String REQUEST_LIST_LIKE = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.id, company.name FROM computer LEFT JOIN company ON company.id = computer.company_id WHERE computer.name LIKE :search";
	private static final String REQUEST_LIMIT_OFFSET = " LIMIT :limit OFFSET :offset";

	private static final Logger LOGGER = LoggerFactory.getLogger(DAOComputer.class);

	protected DataSource connect;
	MapperComputer mapComputer;
	ComputerRowMapper computerRowMapper;
	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public DAOComputer(DataSource connect, MapperComputer mapperComputer, ComputerRowMapper computerRowMapper,
			JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.connect = connect;
		this.mapComputer = mapperComputer;
		this.computerRowMapper = computerRowMapper;
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public void create(Computer computer) {
		
		try {
			SqlParameterSource namedParameters = new MapSqlParameterSource()
					.addValue("name", computer.getName())
					.addValue("introduced", computer.getIntroduced() != null ? java.sql.Date.valueOf(computer.getIntroduced()) : null)
					.addValue("discontinued", computer.getDiscontinued() != null ? java.sql.Date.valueOf(computer.getDiscontinued()) : null)
					.addValue("companyId", computer.getCompany().getId() != null && computer.getCompany().getId() != 0 ? computer.getCompany().getId() : null);
			
			namedParameterJdbcTemplate.update(REQUEST_CREATE, namedParameters);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}

	}

	public void delete(Long id) {

		try {
			SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id != null && id != 0 ? id : null);
			
			namedParameterJdbcTemplate.update(REQUEST_DELETE, namedParameters);
		} catch (DataAccessException e1) {
			// TODO Auto-generated catch block
			LOGGER.error(e1.getMessage());
		}
	}
	
	public void updateById(Long id, Computer computer) {
		
		try {
			SqlParameterSource namedParameters = new MapSqlParameterSource()
					.addValue("1", computer.getId())
					.addValue("2", computer.getName())
					.addValue("3", (computer.getIntroduced() != null ? java.sql.Date.valueOf(computer.getIntroduced()) : null))
					.addValue("4", (computer.getDiscontinued() != null ? java.sql.Date.valueOf(computer.getDiscontinued()) : null))
					.addValue("5", (computer.getCompany().getId() != null && computer.getCompany().getId() != 0 ? computer.getCompany().getId() : null))
					.addValue("6", id);
			
			namedParameterJdbcTemplate.update(REQUEST_UPDATE_BY_ID, namedParameters);
		} catch (DataAccessException e1) {
			LOGGER.error(e1.getMessage()+e1.getStackTrace());
		}
		

	}

	public void updateByName(String name, Computer computer) {

		try {
			SqlParameterSource namedParameters = new MapSqlParameterSource()
					.addValue("1", computer.getId())
					.addValue("2", computer.getName())
					.addValue("3", (computer.getIntroduced() != null ? java.sql.Date.valueOf(computer.getIntroduced()) : null))
					.addValue("4", (computer.getDiscontinued() != null ? java.sql.Date.valueOf(computer.getDiscontinued()) : null))
					.addValue("5", (computer.getCompany().getId() != null && computer.getCompany().getId() != 0 ? computer.getCompany().getId() : null))
					.addValue("6", name);
			
			namedParameterJdbcTemplate.update(REQUEST_UPDATE_BY_NAME, namedParameters);
		} catch (DataAccessException e1) {
			LOGGER.error(e1.getMessage()+e1.getStackTrace());
		}

	}

	public Optional<Computer> showDetailsWithId(Long id) {
		Computer computer = new Computer.ComputerBuilder().build();

		try {
			computer = jdbcTemplate.queryForObject(REQUEST_DETAILS_WHITH_ID + id, computerRowMapper);
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage() + e.getStackTrace());
		}

		return Optional.ofNullable(computer);
	}

	public Optional<Computer> showDetailsWithName(String name) {

		Computer computer = new Computer.ComputerBuilder().build();
		try {
			computer = jdbcTemplate.queryForObject(REQUEST_DETAILS_WHITH_NAME + "'" + name + "'", computerRowMapper);
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage() + e.getStackTrace());
		}
		return Optional.ofNullable(computer);
	}

	public List<Optional<Computer>> list(Page page) {
		List<Optional<Computer>> listeOptionalComputer = new ArrayList<Optional<Computer>>();
		List<Computer> listeComputer = new ArrayList<Computer>();
		try {
			SqlParameterSource namedParameters = new MapSqlParameterSource()
					.addValue("search", "%" + page.getSearchComputer() + "%").addValue("limit", page.getLimit())
					.addValue("offset", page.getOffset());

			listeComputer = namedParameterJdbcTemplate.query(
					REQUEST_LIST_LIKE + " ORDER BY " + page.getOrderBy() + REQUEST_LIMIT_OFFSET, namedParameters,
					computerRowMapper);
			page.setNbrComputer(sizeListComputer(page));
			listeOptionalComputer = listeComputer.stream().map(x -> Optional.ofNullable(x))
					.collect(Collectors.toList());

		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage() + e.getStackTrace());
		}
		return listeOptionalComputer;
	}

	public int sizeListComputer(Page page) {

		try {
			SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("count",
					"%" + page.getSearchComputer() + "%");
			return namedParameterJdbcTemplate.queryForObject(NBR_COMPUTER, namedParameters, Integer.class);
		} catch (DataAccessException e) {
			LOGGER.error(e.getMessage() + e.getStackTrace());
		}

		return -1;
	}

}
