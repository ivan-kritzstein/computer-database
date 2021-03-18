package com.excilys.formation.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.Dto.ComputerDto;
import com.excilys.formation.mapper.MapperComputerDto;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.view.Page;

/**
 * Servlet implementation class ComputerServlet
 */
@WebServlet("/ComputerServlet")
public class ComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NBR_COMUTERS = "numberComputer";
	private static final String LIST_COMPUTERS = "listComputer";

	Computer computer;
	Company company;
	ComputerService computerService = new ComputerService();
	Page page = new Page();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ComputerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
// set un string constant ex nmbr ordi
	// dans le string : id de l'objet sur la jsp
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<ComputerDto> listeComputerDto = new ArrayList<ComputerDto>();
		
		listeComputerDto = MapperComputerDto.computerToListComputerDto(computerService.printComputerService(computerService.listComputerService(), page));
		int nbrComputer = listeComputerDto.size();
		System.out.println(nbrComputer);
		
		request.setAttribute(NBR_COMUTERS,listeComputerDto.size());
		
		request.setAttribute(LIST_COMPUTERS, listeComputerDto);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
