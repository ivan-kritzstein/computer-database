package com.excilys.formation.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.Dto.CompanyDto;
import com.excilys.formation.Dto.ComputerDto;
import com.excilys.formation.mapper.MapperCompanyDto;
import com.excilys.formation.mapper.MapperComputerDto;
import com.excilys.formation.model.Computer;
import com.excilys.formation.service.CompanyService;
import com.excilys.formation.service.ComputerService;

/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/AddComputerServlet")
public class AddComputerServlet extends HttpServlet {
	
	private static final String COMPUTER_NAME = "computerName";
	private static final String INTRODUCED = "introduced";
	private static final String DISCONTINUED = "discontinued";
	private static final String COMPANY_ID = "companyId";
	private static final String LIST_COMPANY = "listCompany";
	private static final long serialVersionUID = 1L;
	ComputerService computerService = new ComputerService();
	CompanyService companyService = new CompanyService();
	Computer computer;
	ComputerDto cmptDto;
	CompanyDto cmpnDto;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<CompanyDto> listCmpnDto = new ArrayList<CompanyDto>();
		
		listCmpnDto = MapperCompanyDto.companyToListCompanyDto(companyService.listCompaniesService());
		
		for (CompanyDto c : listCmpnDto) {
			System.out.println(c);
		}
		
		request.setAttribute(LIST_COMPANY, listCmpnDto);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String computerName = request.getParameter(COMPUTER_NAME);
		String introduced = request.getParameter(INTRODUCED);
		String discontinued = request.getParameter(DISCONTINUED);
		String companyId = request.getParameter(COMPANY_ID);
		
		cmpnDto = new CompanyDto(companyId,null);
		cmptDto = new ComputerDto.ComputerDtoBuilder().setName(computerName).setIntroduced(introduced).setDiscontinued(discontinued).setCompanyDto(cmpnDto).build();
		computer = MapperComputerDto.computerDtoToComputer(cmptDto).orElse(computer);
		computerService.createComputerService(computer);
		
		doGet(request, response);
	}

}