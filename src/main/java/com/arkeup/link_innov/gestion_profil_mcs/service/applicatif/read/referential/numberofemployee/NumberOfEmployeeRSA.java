package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.referential.numberofemployee;

import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.NumberOfEmployeesDTO;

public interface NumberOfEmployeeRSA {
	NumberOfEmployeesDTO listNumberOfEmployees(Pageable pageable);
}
