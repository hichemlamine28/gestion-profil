package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import org.springframework.data.domain.Page;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class CorporationsDTO extends BaseDTO {
	private Page<CorporationDTO> corporationDTOs;

	public Page<CorporationDTO> getCorporationDTOs() {
		return corporationDTOs;
	}

	public void setCorporationDTOs(Page<CorporationDTO> corporationDTOs) {
		this.corporationDTOs = corporationDTOs;
	}

}
