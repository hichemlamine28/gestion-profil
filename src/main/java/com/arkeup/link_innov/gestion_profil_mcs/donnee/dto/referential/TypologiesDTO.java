package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential;

import org.springframework.data.domain.Page;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class TypologiesDTO extends BaseDTO {
	
	Page<TypologyDTO> typologiesDTO;

	public Page<TypologyDTO> getTypologiesDTO() {
		return typologiesDTO;
	}

	public void setTypologiesDTO(Page<TypologyDTO> typologiesDTO) {
		this.typologiesDTO = typologiesDTO;
	}
	
}
