package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import org.springframework.data.domain.Page;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class SkillsDTO extends BaseDTO {
	
	private Page<SkillDTO> skillDTOs;

	public Page<SkillDTO> getSkillDTOs() {
		return skillDTOs;
	}

	public void setSkillDTOs(Page<SkillDTO> skillDTOs) {
		this.skillDTOs = skillDTOs;
	}

}
