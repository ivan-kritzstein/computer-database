package com.excilys.formation.validation;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.excilys.formation.Dto.AddComputerDto;

@Component
public class ValidationComputer implements Validator {

	private void nameComputerDtoValidation(AddComputerDto computerDto, Errors errors) {
		if ("" == computerDto.getName() || null == computerDto.getName() || computerDto.getName().isBlank()) {
			errors.rejectValue("name", "lbl.valid.name");
		}
	}

	private void introducedBeforDiscontinuedValidation(AddComputerDto computerDto, Errors errors) {

		if (null != computerDto.getIntroduced() && computerDto.getIntroduced().compareTo("") != 0
				&& null != computerDto.getDiscontinued() && computerDto.getDiscontinued().compareTo("") != 0) {
			LocalDate introduced = LocalDate.parse(computerDto.getIntroduced());
			LocalDate discontinued = LocalDate.parse(computerDto.getDiscontinued());

			if (introduced.isAfter(discontinued) || introduced.isEqual(discontinued)) {
				errors.rejectValue("introduced", "lbl.valid.introducedDate");
			}
		}
	}
	private void discontinuedAfterIntroducedValidation(AddComputerDto computerDto, Errors errors) {

		if (null != computerDto.getIntroduced() && computerDto.getIntroduced().compareTo("") != 0
				&& null != computerDto.getDiscontinued() && computerDto.getDiscontinued().compareTo("") != 0) {
			LocalDate introduced = LocalDate.parse(computerDto.getIntroduced());
			LocalDate discontinued = LocalDate.parse(computerDto.getDiscontinued());

			if (discontinued.isBefore(introduced) || discontinued.isEqual(introduced)) {
				errors.rejectValue("discontinued", "lbl.valid.discontinuedDate");
			}
		}
	}



	public boolean deleteValidation(String s) {
		boolean verif = true;

		if (s == null || s == "") {
			verif = false;
		}

		return verif;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub

		return AddComputerDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub

		AddComputerDto computerDto = (AddComputerDto) target;
		nameComputerDtoValidation(computerDto, errors);
		introducedBeforDiscontinuedValidation(computerDto, errors);
		discontinuedAfterIntroducedValidation(computerDto, errors);

	}

}
