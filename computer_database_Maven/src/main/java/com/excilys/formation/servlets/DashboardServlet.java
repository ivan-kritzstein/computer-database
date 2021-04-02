package com.excilys.formation.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

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
//@RequestMapping("/ComputerServlet")
@Component
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
//	private static final String DELETE_COMPUTER = "deleteComputer";
	private static final String INDEX_1 = "index1";
	private static final String INDEX_2 = "index2";
	private static final String INDEX_3 = "index3";
	private static final String INDEX_4 = "index4";
	private static final String INDEX_5 = "index5";

	Computer computer;
	Company company;
	@Autowired
	ComputerService computerService;
	ValidationComputer verif = new ValidationComputer();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

//	@GetMapping
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<ListComputerDto> listeComputerDto = new ArrayList<ListComputerDto>();

		HttpSession session = request.getSession();
		if (session.getAttribute(PAGE) == null) {
			session.setAttribute(PAGE, new Page());
		}
		Page page = (Page) session.getAttribute(PAGE);

		page = buttonLimit(page, request);
		page = orderBy(page, request);
		page = searchName(page, request);
		page = indexPage(page, request);

		listeComputerDto = MapperComputerDto
				.listOptionalComputerToListComputerDto(computerService.printComputerService(page));
		request.setAttribute(LIST_COMPUTERS_LIMIT_10, listeComputerDto);

		session.setAttribute(PAGE, page);
//		request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (verif.deleteValidation(request.getParameter("selection"))) {
			String selection2 = request.getParameter("selection");
			String[] selectionTab = selection2.split(",");

			for (String s : selectionTab) {
				Long id = Long.valueOf(s);
				computerService.deleteComputerService(id);
			}
		}

		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	 @Override
	    public void init(ServletConfig config) throws ServletException {
	        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	        super.init(config);
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

	public Page indexPage(Page page, HttpServletRequest request) {
		page.indexPage();
		request.setAttribute(INDEX_1, page.getIndex1());
		request.setAttribute(INDEX_2, page.getIndex2());
		request.setAttribute(INDEX_3, page.getIndex3());
		request.setAttribute(INDEX_4, page.getIndex4());
		request.setAttribute(INDEX_5, page.getIndex5());

		if (request.getParameter("offset") != null) {
			page.setOffset(Integer.parseInt(request.getParameter("offset")) * page.getLimit());
		}

		return page;
	}
}
