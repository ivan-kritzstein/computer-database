package com.excilys.formation.apis;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.excilys.formation.Dto.AddComputerDto;
import com.excilys.formation.Dto.ListComputerDto;
import com.excilys.formation.exception.WebServiceException;
import com.excilys.formation.mapper.MapperComputerDto;
import com.excilys.formation.model.Computer;
import com.excilys.formation.model.Page;
import com.excilys.formation.service.ComputerService;
import com.excilys.formation.validation.ValidationComputer;

@SessionAttributes("page")
@RestController
public class ComputerAPIs {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComputerAPIs.class);
	
	@Autowired
	ComputerService computerService;
	@Autowired
	ValidationComputer validationComputer;
	@Autowired
	Page page;

	Computer computer;

	@GetMapping(value = "/computers", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ListComputerDto> dashboardGet() {

		List<ListComputerDto> listeComputerDto = new ArrayList<ListComputerDto>();
		listeComputerDto = MapperComputerDto
				.listOptionalComputerToListComputerDto(computerService.printComputerService(page));

		return listeComputerDto;
	}

	// il reste a gérer le cas ou pas de request param dans la requete car pas pris en compte avec une nullpointerException
	
	@GetMapping(value = "/searchById", produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<?> searchById(@RequestParam Long id) { // throws NoSuchFieldException {

		AddComputerDto addComputerDto = new AddComputerDto();
		try {
			Computer computer = computerService.showComputerDetailsIdService(id).orElseThrow(() -> new NullPointerException());
			addComputerDto = MapperComputerDto.computerToAddComputerDto(computer);
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>("id recherché n'existe pas", HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(addComputerDto,HttpStatus.OK);
	}
//	*******************************************************************************************************************
//						UTILISATION CUSTOM EXCEPTION ET RESPONSE ENTITY
//	@GetMapping(value = "/searchById", produces = MediaType.APPLICATION_JSON_VALUE)
//	public  ResponseEntity<?> searchById(@RequestParam Long id) { // throws NoSuchFieldException {
//
//		AddComputerDto addComputerDto = new AddComputerDto();
//		try {
//			Computer computer = computerService.showComputerDetailsIdService(id).orElseThrow(() -> new WebServiceException("id recherché n'existe pas"));
//			addComputerDto = MapperComputerDto.computerToAddComputerDto(computer);
//		} catch (WebServiceException e) {
//			// TODO Auto-generated catch block
//			return new ResponseEntity<>(e.getMessage() + e.getStackTrace(), HttpStatus.BAD_REQUEST);
//		}
//		
//		
//		return new ResponseEntity<>(addComputerDto,HttpStatus.OK);
//	}
//	*******************************************************************************************************************



	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/addComp", produces = MediaType.APPLICATION_JSON_VALUE)
	public AddComputerDto addComputer(@RequestBody(required = false) AddComputerDto cmptDto, BindingResult br) {

		validationComputer.validate(cmptDto, br);
		if (!br.hasErrors()) {
			Optional<Computer> computer = MapperComputerDto.addComputerDtoToComputer(cmptDto);
			computerService.createComputerService(computer.get());
			LOGGER.info("l'ordi a bien été créé");
		} else {
			LOGGER.info("échec création");
		}

		return cmptDto;
	}

	@PutMapping(value = "/editComp", produces = MediaType.APPLICATION_JSON_VALUE)
	public AddComputerDto editComputer(@RequestBody(required = false) AddComputerDto cmptDto, BindingResult br) {

		validationComputer.validate(cmptDto, br);
		if (!br.hasErrors()) {
			computer = MapperComputerDto.addComputerDtoToComputer(cmptDto).orElse(computer);
			computerService.updateComputerService(computer.getId(), computer);
			LOGGER.info("l'ordi a bien été modifié");
		} else {
			LOGGER.info("échec de modification");
		}

		return cmptDto;
	}

	@DeleteMapping(value = "/deleteComp", produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus deleteComputer(@RequestParam(required = false) Long id) {

		computerService.deleteComputerService(id);

		return HttpStatus.OK;
	}
}
