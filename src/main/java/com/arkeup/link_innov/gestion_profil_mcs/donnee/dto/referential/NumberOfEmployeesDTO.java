package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential;

import org.springframework.data.domain.Page;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class NumberOfEmployeesDTO extends BaseDTO {
	
	private Page<NumberOfEmployeeDTO> numberOfEmployeesDTO;

	public Page<NumberOfEmployeeDTO> getNumberOfEmployees() {
		return numberOfEmployeesDTO;
	}

	public void setNumberOfEmployees(Page<NumberOfEmployeeDTO> numberOfEmployees) {
		this.numberOfEmployeesDTO = numberOfEmployees;
	}
	
	
}
