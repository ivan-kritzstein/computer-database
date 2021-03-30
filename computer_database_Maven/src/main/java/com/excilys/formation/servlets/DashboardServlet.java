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

import com.excilys.formation.Dto.ListComputerDto;
import com.excilys.formation.mapper.MapperComputerDto;
import com.excilys.formation.model.Company;
import com.excilys.formation.model.Computer;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.validation.ValidationComputer;
import com.excilys.formation.view.Page;

/**
 * Servlet implementation class ComputerServlet
 */
@WebServlet("/ComputerServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE = "page";
	private static final String LIST_COMPUTERS_LIMIT_10 = "listComputerLimit10";
	private static final int LIMIT_10 = 10;
	private static final int LIMIT_50 = 50;
	private static final int LIMIT_100 = 100;
	private static final String ORDER_COMPUTER = "computer.name";
	private static final String ORDER_INTRODUCED = "computer.introduced";
	private static final String ORDER_DISCONTINUED = "computer.discontinued";
	private static final String ORDER_COMPANY = "company.name";
	private static final String DELETE_COMPUTER = "deleteComputer";

	Computer computer;
	Company company;
	ComputerService computerService = new ComputerService();
	ValidationComputer verif = new ValidationComputer();
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

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<ListComputerDto> listeComputerDto = new ArrayList<ListComputerDto>();
//		List<ListComputerDto> listeComputerDto50 = new ArrayList<ListComputerDto>();
//		List<ListComputerDto> listeComputerDto100 = new ArrayList<ListComputerDto>();

		HttpSession session = request.getSession();
		if (session.getAttribute(PAGE) == null) {
			session.setAttribute(PAGE, new Page());
		}
		Page page = (Page) session.getAttribute(PAGE);
//		if(session.getAttribute(LIST_COMPUTERS_LIMIT_50) == null) {
//			session.setAttribute(LIST_COMPUTERS_LIMIT_50, listeComputerDto50);
//		} else if(session.getAttribute(LIST_COMPUTERS_LIMIT_100) == null ) {
//			session.setAttribute(LIST_COMPUTERS_LIMIT_100, listeComputerDto100);
//		} else {
//			session.setAttribute(LIST_COMPUTERS_LIMIT_10, listeComputerDto);
//		}

		page = buttonLimit(page, request);
		page = orderBy(page, request);
		page = searchName(page, request);
		listeComputerDto = MapperComputerDto.listOptionalComputerToListComputerDto(
				computerService.printComputerService(computerService.listComputerService(), page));
		request.setAttribute(LIST_COMPUTERS_LIMIT_10, listeComputerDto);

		session.setAttribute(PAGE, page);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String selection2 =  request.getParameter("selection");
		String[] selectionTab = selection2.split(","); 
		
		for (String s : selectionTab) {
			if (verif.deleteValidation(s)) {
			Long id = Long.valueOf(s);
			computerService.deleteComputerService(id);
			}
		}
//		System.out.println(selection2);
//		System.out.println("le string selec2 est au dessus");

		
//		for (String s : selectionTab) {
//			
//			System.out.println("1");
//			System.out.println(s);
//			System.out.println("2");
//		}
		
//		if (selectionList != null) {
//			for (String s : selectionList) {
//				System.out.println("coucou");
//				System.out.println(s);
//				System.out.println("coucou2");
//			}
//		}
//		for (String s : selectionList) {
//			computerService.deleteComputerService(Long.valueOf(s));
//		}

		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public Page buttonLimit(Page page, HttpServletRequest request) {
		if (request.getParameter("limit") != null) {
			if (request.getParameter("limit").toString().equals("10")) {
				page.setLimit(LIMIT_10);
			} else if (request.getParameter("limit").toString().equals("50")) {
				page.setLimit(LIMIT_50);
			} else if (request.getParameter("limit").toString().equals("100")) {
				page.setLimit(LIMIT_100);
			}
		}
		return page;
	}

	public Page orderBy(Page page, HttpServletRequest request) {
		if (request.getParameter("order") != null) {
			if (request.getParameter("order").toString().equals(ORDER_COMPUTER)) {
				page.setOrderBy(ORDER_COMPUTER);
			} else if (request.getParameter("order").toString().equals(ORDER_INTRODUCED)) {
				page.setOrderBy(ORDER_INTRODUCED);
			} else if (request.getParameter("order").toString().equals(ORDER_DISCONTINUED)) {
				page.setOrderBy(ORDER_DISCONTINUED);
			} else if (request.getParameter("order").toString().equals(ORDER_COMPANY)) {
				page.setOrderBy(ORDER_COMPANY);
			}
		}
		return page;
	}
	
	public Page searchName(Page page, HttpServletRequest request) {
		if (request.getParameter("search") != null) {
			String s = request.getParameter("search");
			page.setSearchComputer(s);
		}
		return page;
	}

}
