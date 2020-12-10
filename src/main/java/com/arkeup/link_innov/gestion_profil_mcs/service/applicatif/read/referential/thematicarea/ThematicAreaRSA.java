package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.referential.thematicarea;

import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ThematicAreasDTO;

public interface ThematicAreaRSA {
	ThematicAreasDTO listThematicAreas(Pageable pageable);
}
