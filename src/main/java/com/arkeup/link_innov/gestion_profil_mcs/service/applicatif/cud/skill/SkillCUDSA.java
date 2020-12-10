package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.skill;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.RecommandationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.SkillDTO;

public interface SkillCUDSA {
	
	public SkillDTO create(String username, SkillDTO skillDTO);
	public SkillDTO update(SkillDTO skillDTO);
	public SkillDTO delete(String id);
	public RecommandationDTO addRecommendation(RecommandationDTO recommendation, String username);
	public RecommandationDTO deleteRecommendation(RecommandationDTO recommendation, String username);
}
