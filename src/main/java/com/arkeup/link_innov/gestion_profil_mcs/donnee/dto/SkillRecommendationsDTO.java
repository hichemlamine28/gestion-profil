package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import org.springframework.data.domain.Page;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class SkillRecommendationsDTO extends BaseDTO {
	private Page<SkillRecommendationDTO> skillDTOs;

	public Page<SkillRecommendationDTO> getSkillDTOs() {
		return skillDTOs;
	}

	public void setSkillDTOs(Page<SkillRecommendationDTO> skillDTOs) {
		this.skillDTOs = skillDTOs;
	}

}
