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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

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
 * Servlet implementation class AddComputerServlet
 */
@Controller
@WebServlet("/AddComputerServlet")
public class AddComputerServlet extends HttpServlet {

	private static final String COMPUTER_NAME = "computerName";
	private static final String INTRODUCED = "introduced";
	private static final String DISCONTINUED = "discontinued";
	private static final String COMPANY_ID = "companyId";
	private static final String LIST_COMPANY = "listCompany";
	private static final long serialVersionUID = 1L;
	@Autowired
	ComputerService computerService;
	@Autowired
	CompanyService companyService;
	@Autowired
	ValidationComputer validationComputer;
	
	Computer computer;
	AddComputerDto cmptDto;
	
	private static Logger LOGGER = LoggerFactory.getLogger(AddComputerServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
//	public AddComputerServlet() {
//		super();
//		// TODO Auto-generated constructor stub
//	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	@Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
        super.init(config);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<CompanyDto> listCmpnDto = new ArrayList<CompanyDto>();

		listCmpnDto = MapperCompanyDto.listOptionalCompanyToListCompanyDto(companyService.listCompaniesService());
		request.setAttribute(LIST_COMPANY, listCmpnDto);

		this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub

		String computerName = request.getParameter(COMPUTER_NAME);
		String introduced = request.getParameter(INTRODUCED);
		String discontinued = request.getParameter(DISCONTINUED);
		String companyId = request.getParameter(COMPANY_ID);

//		cmpnDto = new String(companyId, null);
	
		cmptDto = new AddComputerDto.AddComputerDtoBuilder().setName(computerName).setIntroduced(introduced)
				.setDiscontinued(discontinued).setCompanyId(companyId).build();
		computer = MapperComputerDto.addComputerDtoToComputer(cmptDto).orElse(computer);
//		cmptDto = new ListComputerDto.ListComputerDtoBuilder().setName(computerName).setIntroduced(introduced)
//				.setDiscontinued(discontinued).setCompanyName(cmpnDto).build();
//		computer = MapperComputerDto.computerDtoToComputer(cmptDto).orElse(computer);
		System.out.println(computer);
		if (validationComputer.computerValidation(computer)) {
		computerService.createComputerService(computer);
		}
		else {
			LOGGER.error("il y a une erreur", new InputException("ko"));
		}
		doGet(request, response);
	}

}
