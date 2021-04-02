package com.excilys.formation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.DAO.DAOComputer;
import com.excilys.formation.model.Computer;
import com.excilys.formation.view.Page;
@Component
public class ComputerService {
	@Autowired 
	DAOComputer daocomputer;


	
	public List<Optional<Computer>> printComputerService(Page page) {
		return daocomputer.list(page);
	}
	
	public Optional<Computer> showComputerDetailsIdService(Long idDetails) {
		return daocomputer.showDetailsWithId(idDetails);
	}
	
	public Optional<Computer> showComputerDetailsNameService(String nameDetails) {
		return daocomputer.showDetailsWithName(nameDetails);
	}
	public void createComputerService(Computer computer) {
		daocomputer.create(computer);
	}
	public void updateComputerService(Long idAModifier, Computer computer) {
		daocomputer.updateById(idAModifier, computer);
	}
	public void deleteComputerService(Long id) {
		daocomputer.delete(id);
	}
}
