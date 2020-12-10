package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.thematicarea;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ThematicAreaDTO;

public interface ThematicAreaCUDSA {

	public ThematicAreaDTO addThematicArea(ThematicAreaDTO thematicAreaDTO);

	public ThematicAreaDTO updateThematicArea(ThematicAreaDTO thematicAreasDTO);

	public ThematicAreaDTO deleteThematicArea(String idThematicArea);

	public void initDatabase();

}
