package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.referential.numberofemployee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.NumberOfEmployee;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.NumberOfEmployeeRepository;

@Service
public class NumberOfEmployeeCUDSMImpl implements NumberOfEmployeeCUDSM {

	@Autowired
	private NumberOfEmployeeRepository numberOfEmployeeRepository;
	
	@Override
	public NumberOfEmployee create(NumberOfEmployee numberOfEmployee) {
		numberOfEmployeeRepository.save(numberOfEmployee);
		return numberOfEmployee;
	}

	@Override
	public void update(NumberOfEmployee numberOfEmployee) {
		numberOfEmployeeRepository.save(numberOfEmployee);
	}

	@Override
	public void delete(String id) {
		numberOfEmployeeRepository.deleteById(id);
	}

}
