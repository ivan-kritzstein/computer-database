package com.excilys.formation.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.DataSource;
//ZJSF2YhmC2wtaVkiiH
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

import com.excilys.formation.Dto.ComputerHQLDto;
import com.excilys.formation.mapper.ComputerRowMapper;
import com.excilys.formation.mapper.MapperComputerDto;
import com.excilys.formation.model.Computer;
import com.excilys.formation.view.Page;

@Repository
public class DAOComputer {

	private static final String REQUEST_HQL_LIST_LIKE = "FROM ComputerHQLDto computer LEFT JOIN FETCH computer.companyHQLDto WHERE computer.name LIKE :search";
	private static final String NBR_HQL_COMPUTER = "SELECT COUNT(id) FROM ComputerHQLDto computer WHERE computer.name LIKE :count";
	private static final String REQUEST_HQL_DETAILS_WHITH_NAME = "FROM ComputerHQLDto computer LEFT JOIN FETCH CompanyHQLDto WHERE computer.name = ";
	private static final String REQUEST_HQL_DETAILS_WHITH_ID = "FROM ComputerHQLDto computer LEFT JOIN FETCH computer.companyHQLDto WHERE computer.id = ";
	private static final String REQUEST_HQL_UPDATE_BY_NAME = "UPDATE ComputerHQLDto computer SET computer.id =:id, computer.name =:name, computer.introduced =:introduced, computer.discontinued =:discontinued, computer.companyHQLDto.id =:companyId WHERE computer.name =:name2";
	private static final String REQUEST_HQL_UPDATE_BY_ID = "UPDATE ComputerHQLDto computer SET computer.id = :id, computer.name = :name, computer.introduced = :introduced, computer.discontinued = :discontinued, computer.companyHQLDto.id = :companyId WHERE computer.id = :id2";
	private static final String REQUEST_HQL_DELETE = "delete FROM ComputerHQLDto computer where computer.id=:id";

	private static final Logger LOGGER = LoggerFactory.getLogger(DAOComputer.class);

	protected DataSource connect;
	ComputerRowMapper computerRowMapper;
	JdbcTemplate jdbcTemplate;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	LocalSessionFactoryBean sessionFactory;

	@Autowired
	public DAOComputer(DataSource connect, ComputerRowMapper computerRowMapper, JdbcTemplate jdbcTemplate,
			NamedParameterJdbcTemplate namedParameterJdbcTemplate, LocalSessionFactoryBean sessionFactory) {
		this.connect = connect;
		this.computerRowMapper = computerRowMapper;
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.sessionFactory = sessionFactory;
	}


	@Transactional("hibernateTransactionManager")
	public void create(Computer computer) {

		try {
			Session session = sessionFactory.getObject().getCurrentSession();
			session.save(MapperComputerDto.computerToComputerHQLDto(computer));
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage() + e.getStackTrace());
		}

	}


	@Transactional("hibernateTransactionManager")
	public void delete(Long id) {

		try {
			Session session = sessionFactory.getObject().getCurrentSession();
			session.createQuery(REQUEST_HQL_DELETE)
					.setParameter("id", id != null && id != 0 ? id : null).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage() + e.getStackTrace());
		}
	}


	@Transactional("hibernateTransactionManager")
	public void updateById(Long id, Computer computer) {

		try {
			Session session = sessionFactory.getObject().getCurrentSession();
			session.createQuery(REQUEST_HQL_UPDATE_BY_ID)
					.setParameter("id", computer.getId()).setParameter("name", computer.getName())
					.setParameter("introduced",
							computer.getIntroduced() != null ? computer.getIntroduced() : null)
					.setParameter("discontinued",
							computer.getDiscontinued() != null ? computer.getDiscontinued()
									: null)
					.setParameter("companyId",
							computer.getCompany().getId() != null && computer.getCompany().getId() != 0
									? computer.getCompany().getId()
									: null)
					.setParameter("id2", id).executeUpdate();
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage() + e.getStackTrace());
		}
	}

	@Transactional("hibernateTransactionManager")
	public void updateByName(String name, Computer computer) {

		try {
			Session session = sessionFactory.getObject().getCurrentSession();
			session.createQuery(REQUEST_HQL_UPDATE_BY_NAME, ComputerHQLDto.class)
					.setParameter("id", computer.getId())
					.setParameter("name", computer.getName())
					.setParameter("introduced",
							computer.getIntroduced() != null ? computer.getIntroduced() : null)
					.setParameter("discontinued",
							computer.getDiscontinued() != null ? computer.getDiscontinued()
									: null)
					.setParameter("companyId",
							computer.getCompany().getId() != null && computer.getCompany().getId() != 0
									? computer.getCompany().getId()
									: null)
					.setParameter("name2", name).executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage() + e.getStackTrace());
		}

	}


	@Transactional("hibernateTransactionManager")
	public Optional<Computer> showDetailsWithId(Long id) {

		Optional<Computer> computer = Optional.empty();

		try {
			Session session = sessionFactory.getObject().getCurrentSession();
			Query<ComputerHQLDto> query = session.createQuery(REQUEST_HQL_DETAILS_WHITH_ID + id, ComputerHQLDto.class);
			computer = MapperComputerDto.computerHQLDtoToComputer(query.uniqueResult());
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage() + e.getStackTrace());
		}

		return computer;
	}


	@Transactional("hibernateTransactionManager")
	public Optional<Computer> showDetailsWithName(String name) {

		Optional<Computer> computer = Optional.empty();

		try {
			Session session = sessionFactory.getObject().getCurrentSession();
			Query<ComputerHQLDto> query = session.createQuery(REQUEST_HQL_DETAILS_WHITH_NAME + "'" + name + "'",
					ComputerHQLDto.class);
			computer = MapperComputerDto.computerHQLDtoToComputer(query.uniqueResult()); 
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage() + e.getStackTrace());
		}

		return computer;

	}


	@Transactional("hibernateTransactionManager")
	public List<Optional<Computer>> list(Page page) {

		List<Optional<Computer>> listeOptionalComputer = new ArrayList<Optional<Computer>>();
		List<ComputerHQLDto> listeComputerHQLDto = new ArrayList<ComputerHQLDto>();

		Session session = sessionFactory.getObject().getCurrentSession();
		Query<ComputerHQLDto> query = session
				.createQuery(REQUEST_HQL_LIST_LIKE + " ORDER BY " + page.getOrderBy(), ComputerHQLDto.class)
				.setParameter("search", "%" + page.getSearchComputer() + "%").setFirstResult(page.getOffset())
				.setMaxResults(page.getLimit());
		listeComputerHQLDto = query.list();
		listeOptionalComputer = listeComputerHQLDto.stream().map(x -> MapperComputerDto.computerHQLDtoToComputer(x))
				.collect(Collectors.toList());
		page.setNbrComputer(sizeListComputer(page));

		return listeOptionalComputer;

	}


	public int sizeListComputer(Page page) {

		try {
			Session session = sessionFactory.getObject().getCurrentSession();
			Query<Long> query = session.createQuery(NBR_HQL_COMPUTER, Long.class).setParameter("count",
					"%" + page.getSearchComputer() + "%");
			return query.uniqueResult().intValue();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage() + e.getStackTrace());
		}
		return -1;

	}

}
