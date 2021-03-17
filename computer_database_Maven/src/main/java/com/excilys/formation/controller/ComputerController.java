package com.excilys.formation.controller;

import java.util.List;
import java.util.Optional;

import com.excilys.formation.DAO.DAOComputer;
import com.excilys.formation.model.Computer;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.view.Page;

public class ComputerController {
	ComputerService computerService = new ComputerService();


	public DAOComputer listComputerController() {
		return computerService.listComputerService();
	}

	public List<Optional<Computer>> printComputerController(DAOComputer daoc, Page page) {
		return computerService.printComputerService(daoc, page);
	}

	public Optional<Computer> showComputerDetailsIdController(Long idDetails) {
		return computerService.showComputerDetailsIdService(idDetails);
	}

	public Optional<Computer> showComputerDetailsNameController(String nameDetails) {
		return computerService.showComputerDetailsNameService(nameDetails);
	}

	public void createComputerController(Computer computer) {
		computerService.createComputerService(computer);
	}

	public void updateComputerController(Long idAModifier, Computer computer) {
		computerService.updateComputerService(idAModifier, computer);
	}

	public void deleteComputerController(Long id) {
		computerService.deleteComputerService(id);
	}

}
