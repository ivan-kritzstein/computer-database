package com.excilys.formation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.excilys.formation.DAO.DAOComputer;
import com.excilys.formation.model.Computer;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.model.Page;

@Controller
public class ComputerController {
	ComputerService computerService;
	
	@Autowired
	public ComputerController (ComputerService computerService) {
		this.computerService = computerService;
	}


	public DAOComputer listComputerController() {
		return null;
	}

	public List<Optional<Computer>> printComputerController( Page page) {
		return computerService.printComputerService( page);
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
