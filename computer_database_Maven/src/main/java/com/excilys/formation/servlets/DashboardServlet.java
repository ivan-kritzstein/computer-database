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
import javax.servlet.http.HttpSession;

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
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE = "page";
	private static final String LIST_COMPUTERS = "listComputer";

	Computer computer;
	Company company;
	ComputerService computerService = new ComputerService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashboardServlet() {
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
		
		HttpSession session = request.getSession();
		if(session.getAttribute(PAGE) == null) {
			session.setAttribute(PAGE, new Page());
			
		}
		Page page = (Page) session.getAttribute(PAGE);
		
		listeComputerDto = MapperComputerDto.computerToListComputerDto(
				computerService.printComputerService(computerService.listComputerService(), page));

		List<Optional<Computer>> listC = computerService.printComputerService(computerService.listComputerService(), page);
		
		for (Optional<Computer> c : listC) {
			System.out.println(c.orElse(null));
		}

		System.out.println(listeComputerDto.isEmpty());
		request.setAttribute(LIST_COMPUTERS, listeComputerDto);
		
//		for (ComputerDto c : listeComputerDto) {
//			System.out.println(c);
//		}

		session.setAttribute(PAGE, page);
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
