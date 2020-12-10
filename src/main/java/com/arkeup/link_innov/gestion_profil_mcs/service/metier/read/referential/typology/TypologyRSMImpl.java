package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.typology;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Typology;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.TypologyRepository;

@Service
public class TypologyRSMImpl implements TypologyRSM {

	@Autowired
	TypologyRepository typologyRepository;
	
	@Override
	public Page<Typology> listTypologies(Pageable pageable) {
		return typologyRepository.findAll(pageable);
	}

	@Override
	public Optional<Typology> findById(String id) {
		return typologyRepository.findById(id);
	}

}
