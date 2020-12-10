package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential;

import org.springframework.data.domain.Page;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class ThematicAreasDTO extends BaseDTO {

	private Page<ThematicAreaDTO> thematicAreasDTO;

	public Page<ThematicAreaDTO> getThematicAreasDTO() {
		return thematicAreasDTO;
	}

	public void setThematicAreasDTO(Page<ThematicAreaDTO> thematicAreasDTO) {
		this.thematicAreasDTO = thematicAreasDTO;
	}
	
	
}
