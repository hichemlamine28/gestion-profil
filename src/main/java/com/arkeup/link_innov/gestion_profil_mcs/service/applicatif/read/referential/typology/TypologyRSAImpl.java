package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.referential.typology;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential.TypologyMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.TypologiesDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.typology.TypologyRSM;

@Service
public class TypologyRSAImpl implements TypologyRSA {

	@Autowired
	TypologyRSM typologyRSM;
	
	@Autowired
	TypologyMapper typologyFactory;
	
	@Override
	public TypologiesDTO listTypologies(Pageable pageable) {
		
		TypologiesDTO result = new TypologiesDTO();
		result.setTypologiesDTO(typologyFactory.typologyPageToTypologyDTOPage(typologyRSM.listTypologies(pageable), pageable));
		result.setError(false);
		result.setMessage("List of all typologies");
		return result;
	}

}
