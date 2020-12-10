package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.numberofemployee;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.NumberOfEmployee;

public interface NumberOfEmployeeRSM {
	Page<NumberOfEmployee> listNumberOfEmployees(Pageable pageable);
	Optional<NumberOfEmployee> findById(String id);
}
