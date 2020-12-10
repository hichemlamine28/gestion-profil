package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.skill;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.SkillDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.SkillRecommendationsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.SkillsDTO;

public interface SkillRSA {
	;

	SkillDTO getSkill(String id);

	SkillRecommendationsDTO listSkills(String username, Pageable pageable);

	SkillsDTO findSkill(String name, Pageable pageable);

	SkillsDTO getSkills(String username, Pageable pageable);

	// TODO
	List<String> findUsersBySkillLabel(String label);

}
