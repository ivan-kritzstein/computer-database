package com.excilys.formation.validation;

import org.springframework.stereotype.Component;

import com.excilys.formation.model.Computer;

@Component
public class ValidationComputer {

	public boolean computerValidation(Computer computer) {
		boolean verif = true;
		if ("" == computer.getName() || null == computer.getName() || computer.getName().isBlank()) {
			verif = false;
		}

		if (null != computer.getIntroduced() && null != computer.getDiscontinued()) {
			if (computer.getIntroduced().isAfter(computer.getDiscontinued())
					|| computer.getIntroduced().isEqual(computer.getDiscontinued())) {
				verif = false;
			}
		}
		
		return verif;

	}
	
	public boolean deleteValidation(String s) {
		boolean verif = true;
		
		if(s == null || s == "") {
			verif = false;
		}
		
		return verif;
	}
}
