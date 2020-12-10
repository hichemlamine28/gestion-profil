package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.referential.typology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Typology;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.TypologyRepository;

@Service
public class TypologyCUDSMImpl implements TypologyCUDSM {

	@Autowired
	private TypologyRepository typologyRepository;
	
	@Override
	public Typology create(Typology typology) {
		typologyRepository.save(typology);
		return typology;
	}

	@Override
	public void update(Typology typology) {
		typologyRepository.save(typology);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		typologyRepository.deleteById(id);
	}

}
