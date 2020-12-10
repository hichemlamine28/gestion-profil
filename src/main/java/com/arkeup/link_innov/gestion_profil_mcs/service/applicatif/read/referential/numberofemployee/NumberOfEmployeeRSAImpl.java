package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.referential.numberofemployee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential.NumberOfEmployeeMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.NumberOfEmployeesDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.numberofemployee.NumberOfEmployeeRSM;

@Service
public class NumberOfEmployeeRSAImpl implements NumberOfEmployeeRSA {

	@Autowired
	NumberOfEmployeeRSM numberOfEmployeeRSM;
	
	@Autowired
	NumberOfEmployeeMapper numberOfEmployeeFactory;
	
	@Override
	public NumberOfEmployeesDTO listNumberOfEmployees(Pageable pageable) {
		
		NumberOfEmployeesDTO result = new NumberOfEmployeesDTO();
		result.setNumberOfEmployees(numberOfEmployeeFactory.numberOfEmployeePageToNumberOfEmployeeDTOPage(numberOfEmployeeRSM.listNumberOfEmployees(pageable), pageable));
		result.setError(false);
		result.setMessage("List of all numbers of employee");
		return result;
	}

}
