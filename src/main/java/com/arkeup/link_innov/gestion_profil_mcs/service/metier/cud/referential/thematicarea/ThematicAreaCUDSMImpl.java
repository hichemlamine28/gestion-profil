package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.referential.thematicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ThematicArea;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ThematicAreaRepository;

@Service
public class ThematicAreaCUDSMImpl implements ThematicAreaCUDSM {

	@Autowired
	private ThematicAreaRepository thematicAreaRepository;
	
	@Override
	public ThematicArea create(ThematicArea thematicArea) {
		thematicAreaRepository.save(thematicArea);
		return thematicArea;
	}

	@Override
	public void update(ThematicArea thematicArea) {
		thematicAreaRepository.save(thematicArea);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		thematicAreaRepository.deleteById(id);
	}

}
