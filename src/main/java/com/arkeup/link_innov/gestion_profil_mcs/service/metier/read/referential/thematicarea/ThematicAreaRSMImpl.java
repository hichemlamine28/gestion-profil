package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.thematicarea;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ThematicArea;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ThematicAreaRepository;

@Service
public class ThematicAreaRSMImpl implements ThematicAreaRSM {

	@Autowired
	ThematicAreaRepository thematicAreaRepository;
	
	@Override
	public Page<ThematicArea> listThematicAreas(Pageable pageable) {
		return thematicAreaRepository.findAll(pageable);
	}

	@Override
	public Optional<ThematicArea> findById(String id) {
		return thematicAreaRepository.findById(id);
	}

}
