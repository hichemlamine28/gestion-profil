package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.numberofemployee;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.NumberOfEmployee;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.NumberOfEmployeeRepository;

@Service
public class NumberOfEmployeeRSMImpl implements NumberOfEmployeeRSM {

	@Autowired
	NumberOfEmployeeRepository numberOfEmployeeRepository;
	
	@Override
	public Page<NumberOfEmployee> listNumberOfEmployees(Pageable pageable) {
		return numberOfEmployeeRepository.findAll(pageable);
	}

	@Override
	public Optional<NumberOfEmployee> findById(String id) {
		return numberOfEmployeeRepository.findById(id);
	}

}
