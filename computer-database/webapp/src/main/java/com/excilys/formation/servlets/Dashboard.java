package com.excilys.formation.servlets;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.excilys.formation.Dto.ListComputerDto;
import com.excilys.formation.mapper.MapperComputerDto;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.validation.ValidationComputer;
import com.excilys.formation.model.Page;


@SessionAttributes("page")
@Controller
public class Dashboard {
	private static final String PAGE = "page";
	private static final String LIST_COMPUTERS_LIMIT_10 = "listComputerLimit10";
	private static final int LIMIT_10 = 10;
	private static final int LIMIT_50 = 50;
	private static final int LIMIT_100 = 100;
	private static final String ORDER_COMPUTER = "computer.name";
	private static final String ORDER_INTRODUCED = "computer.introduced";
	private static final String ORDER_DISCONTINUED = "computer.discontinued";
	private static final String ORDER_COMPANY = "computer.companyHQLDto.name";
	private static final String INDEX_1 = "index1";
	private static final String INDEX_2 = "index2";
	private static final String INDEX_3 = "index3";
	private static final String INDEX_4 = "index4";
	private static final String INDEX_5 = "index5";
	private static final String DASHBOARD = "dashboard";

	@Autowired
	ComputerService computerService;
	@Autowired
	ValidationComputer validationComputer;
	@Autowired
	Page page;


	@GetMapping("/dashboard")
	public ModelAndView dashboardGet(@RequestParam(required = false) String offset,
			@RequestParam(required = false) String limit, @RequestParam(required = false) String order,
			@RequestParam(required = false) String search) {

		ModelAndView modelAndView = new ModelAndView("dashboard");
		List<ListComputerDto> listeComputerDto = new ArrayList<ListComputerDto>();

		page = buttonLimit(page, limit);
		page = orderBy(page, order);
		page = searchName(page, search);
		page = indexPage(page, offset, modelAndView);

		listeComputerDto = MapperComputerDto
				.listOptionalComputerToListComputerDto(computerService.printComputerService(page));

		modelAndView.setViewName(DASHBOARD);
		modelAndView.addObject(LIST_COMPUTERS_LIMIT_10, listeComputerDto);
		modelAndView.addObject(PAGE, page);
		modelAndView.getModel().put(PAGE, page);

		return modelAndView;
	}

	@PostMapping("/dashboard")
	public ModelAndView dashboardPost(@RequestParam(required = false) String selection) {
		if (validationComputer.deleteValidation(selection)) {
			String selection2 = selection;
			String[] selectionTab = selection2.split(",");

			for (String s : selectionTab) {
				Long id = Long.valueOf(s);
				computerService.deleteComputerService(id);
			}
		}
		return dashboardGet(null, null, null, null);
	}

	public Page buttonLimit(Page page, String limit) {
		if (limit != null) {
			if (limit.toString().equals("10")) {
				page.setLimit(LIMIT_10);
			} else if (limit.toString().equals("50")) {
				page.setLimit(LIMIT_50);
			} else if (limit.toString().equals("100")) {
				page.setLimit(LIMIT_100);
			}
		}
		return page;
	}

	public Page orderBy(Page page, String order) {
		if (order != null) {
			if (order.toString().equals(ORDER_COMPUTER)) {
				page.setOrderBy(ORDER_COMPUTER);
			} else if (order.toString().equals(ORDER_INTRODUCED)) {
				page.setOrderBy(ORDER_INTRODUCED);
			} else if (order.toString().equals(ORDER_DISCONTINUED)) {
				page.setOrderBy(ORDER_DISCONTINUED);
			} else if (order.toString().equals(ORDER_COMPANY)) {
				page.setOrderBy(ORDER_COMPANY);
			}
		}
		return page;
	}

	public Page searchName(Page page, String search) {
		if (search != null) {
			String s = search;
			page.setSearchComputer(s);
		}
		return page;
	}

	public Page indexPage(Page page, String offset, ModelAndView modelAndView) {
		page.indexPage();
		modelAndView.addObject(INDEX_1, page.getIndex1());
		modelAndView.addObject(INDEX_2, page.getIndex2());
		modelAndView.addObject(INDEX_3, page.getIndex3());
		modelAndView.addObject(INDEX_4, page.getIndex4());
		modelAndView.addObject(INDEX_5, page.getIndex5());

		if (offset != null) {
			page.setOffset(Integer.parseInt(offset) * page.getLimit());
		}

		return page;
	}
}
