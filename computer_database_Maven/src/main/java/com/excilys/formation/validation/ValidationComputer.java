package com.excilys.formation.validation;

import com.excilys.formation.model.Computer;

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
		
		if(null != computer.getCompany().getId()) {
			verif = false;
		}

		return verif;

	}
}
