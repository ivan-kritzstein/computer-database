package com.excilys.formation.service;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import com.excilys.formation.DAO.DAOComputer;
import com.excilys.formation.model.Computer;
import com.excilys.formation.view.Page;

public class ComputerService {
	Connection con;

	
	public DAOComputer listComputerService() {
		DAOComputer daoc = new DAOComputer(con);
		return daoc;
	}
	
	public List<Optional<Computer>> printComputerService(DAOComputer daoc, Page page) {
		return daoc.list(page);
	}
	
	public Optional<Computer> showComputerDetailsIdService(Long idDetails) {
		DAOComputer daoc = new DAOComputer(con);
		return daoc.showDetailsWithId(idDetails);
	}
	
	public Optional<Computer> showComputerDetailsNameService(String nameDetails) {
		DAOComputer daoc = new DAOComputer(con);
		return daoc.showDetailsWithName(nameDetails);
	}
	public void createComputerService(Computer computer) {
		DAOComputer daoc = new DAOComputer(con);
		daoc.create(computer);
	}
	public void updateComputerService(Long idAModifier, Computer computer) {
		DAOComputer daoc = new DAOComputer(con);
		daoc.updateById(idAModifier, computer);
	}
	public void deleteComputerService(Long id) {
		DAOComputer daoc = new DAOComputer(con);
		daoc.delete(id);
	}
}
