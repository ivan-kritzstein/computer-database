package com.excilys.formation.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.Dto.AddComputerDto;
import com.excilys.formation.Dto.CompanyDto;
import com.excilys.formation.exceptions.InputException;
import com.excilys.formation.mapper.MapperCompanyDto;
import com.excilys.formation.mapper.MapperComputerDto;
import com.excilys.formation.model.Computer;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.validation.ValidationComputer;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String COMPUTER_NAME = "computerName";
	private static final String INTRODUCED = "introduced";
	private static final String DISCONTINUED = "discontinued";
	private static final String COMPANY_ID = "companyId";
	private static final String LIST_COMPANY = "listCompany";
	private static final String LOADED_COMPUTER = "loadedComputer";
	private static final String ID = "id";
	private static Logger LOGGER = LoggerFactory.getLogger(AddComputerServlet.class);

	ComputerService computerService = new ComputerService();
	CompanyService companyService = new CompanyService();
	Computer computer;
	AddComputerDto cmptDto;
	ValidationComputer verif = new ValidationComputer();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		List<CompanyDto> listCmpnDto = new ArrayList<CompanyDto>();

		listCmpnDto = MapperCompanyDto.listOptionalCompanyToListCompanyDto(companyService.listCompaniesService());
		request.setAttribute(LIST_COMPANY, listCmpnDto);
		
		String id = request.getParameter("computerId");
		Long idComputer = Long.valueOf(id);
		Computer computerMemory = computerService.showComputerDetailsIdService(idComputer).orElse(null);
		request.setAttribute(LOADED_COMPUTER, computerMemory);

		this.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String computerName = request.getParameter(COMPUTER_NAME);
		String introduced = request.getParameter(INTRODUCED);
		String discontinued = request.getParameter(DISCONTINUED);
		String companyId = request.getParameter(COMPANY_ID);
		String idLoaded = request.getParameter(ID);
		System.out.println(idLoaded);

		
		cmptDto = new AddComputerDto.AddComputerDtoBuilder().setId(idLoaded).setName(computerName).setIntroduced(introduced)
				.setDiscontinued(discontinued).setCompanyId(companyId).build();
		computer = MapperComputerDto.addComputerDtoToComputer(cmptDto).orElse(computer);
		System.out.println(computer);
		
		if (verif.computerValidation(computer)) {
			computerService.updateComputerService(computer.getId(), computer);
			this.getServletContext().getRequestDispatcher("/ComputerServlet").forward(request, response);
		} else {
			LOGGER.error("il y a une erreur", new InputException("ko"));
		
			doGet(request, response);
		}
		
	}
	
//	public Long loadIdComputer(HttpServletRequest request) {
//		String id = request.getParameter("computerId");
//		
//		return Long.valueOf(id);
//	}
}
