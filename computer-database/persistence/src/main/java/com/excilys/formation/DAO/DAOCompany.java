package com.excilys.formation.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.Dto.CompanyHQLDto;
import com.excilys.formation.mapper.CompanyRowMapper;
import com.excilys.formation.mapper.MapperCompanyDto;
import com.excilys.formation.model.Company;

@Repository
public class DAOCompany {


	private static Logger LOGGER = LoggerFactory.getLogger(DAOCompany.class);

	private static final String REQUEST_HQL_LIST = "FROM CompanyHQLDto";
	private static final String REQUEST_HQL_SHOW_DETAILS_WITH_NAME = "FROM CompanyHQLDto company WHERE company.name  = ";
	private static final String REQUEST_HQL_SHOW_DETAILS_WITH_ID = "FROM CompanyHQLDto company WHERE company.id  = ";
	private static final String REQUEST_HQL_DELETE_COMPUTER_OF_COMPANY = "delete FROM ComputerHQLDto computer WHERE computer.companyHQLDto.id = :id";
	private static final String REQUEST_HQL_DELETE = "delete FROM CompanyHQLDto company WHERE company.id = :id";

	
	protected DataSource connect;
	CompanyRowMapper companyRowMapper;
	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	LocalSessionFactoryBean sessionFactory;

	@Autowired
	public DAOCompany(DataSource connect, CompanyRowMapper companyRowMapper,
			JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, LocalSessionFactoryBean sessionFactory) {

		this.connect = connect;
		this.companyRowMapper = companyRowMapper;
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.sessionFactory = sessionFactory;
	}

	
	@Transactional("txManager") 
	public void delete(Long id) {
		
		try {
			deleteComputerOfCompany(id);
			Session session = sessionFactory.getObject().getCurrentSession();
			session.createQuery(REQUEST_HQL_DELETE)
					.setParameter("id", id).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage() + e.getStackTrace());
		}
	}

	
	@Transactional("hibernateTransactionManager")
	public void deleteComputerOfCompany(Long id) {

		try {
			Session session = sessionFactory.getObject().getCurrentSession();
			session.createQuery(REQUEST_HQL_DELETE_COMPUTER_OF_COMPANY)
					.setParameter("id", id != null && id != 0 ? id : null).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage() + e.getStackTrace());
		}
		
		
	}
	
	@Transactional("hibernateTransactionManager")
	public Optional<Company> showDetailsWithId(Long id) {
		
		Optional<Company> company = Optional.empty();

		try {
			Session session = sessionFactory.getObject().getCurrentSession();
			Query<CompanyHQLDto> query = session.createQuery(REQUEST_HQL_SHOW_DETAILS_WITH_ID + id,
					CompanyHQLDto.class);
			company = MapperCompanyDto.companyHQLDtoToCompany(query.uniqueResult()); 
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage() + e.getStackTrace());
		}
		return company;
	}

	@Transactional("hibernateTransactionManager")
	public Optional<Company> showDetailsWithName(String name) {
		Optional<Company> company = Optional.empty();

		try {
			Session session = sessionFactory.getObject().getCurrentSession();
			Query<CompanyHQLDto> query = session.createQuery(REQUEST_HQL_SHOW_DETAILS_WITH_NAME + "'" + name + "'",
					CompanyHQLDto.class);
			company = MapperCompanyDto.companyHQLDtoToCompany(query.uniqueResult()); 
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage() + e.getStackTrace());
		}
		return company;
	}

	
	@Transactional("hibernateTransactionManager")
	public List<Optional<Company>> list() {
		List<Optional<Company>> listeOptionalCompany = new ArrayList<Optional<Company>>();
		List<CompanyHQLDto> listeCompanyHQLDto = new ArrayList<CompanyHQLDto>();

		try {
			Session session = sessionFactory.getObject().getCurrentSession();
			Query<CompanyHQLDto> query = session
					.createQuery(REQUEST_HQL_LIST, CompanyHQLDto.class);
			listeCompanyHQLDto = query.list();
			listeOptionalCompany = listeCompanyHQLDto.stream().map(x -> MapperCompanyDto.companyHQLDtoToCompany(x)).collect(Collectors.toList());
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage() + e.getStackTrace());
		}
	
		return listeOptionalCompany;
	}
}
