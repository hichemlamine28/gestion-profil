package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.referential.thematicarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential.ThematicAreaMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ThematicAreasDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.thematicarea.ThematicAreaRSM;

@Service
public class ThematicAreaRSAImpl implements ThematicAreaRSA {

	@Autowired
	ThematicAreaRSM thematicAreaRSM;
	
	@Autowired
	ThematicAreaMapper thematicAreaFactory;
	
	@Override
	public ThematicAreasDTO listThematicAreas(Pageable pageable) {
		
		ThematicAreasDTO result = new ThematicAreasDTO();
		result.setThematicAreasDTO(thematicAreaFactory.thematicAreaPageToThematicAreaDTOPage(thematicAreaRSM.listThematicAreas(pageable), pageable));
		result.setError(false);
		result.setMessage("List of all thematic areas");
		return result;
	}

}
