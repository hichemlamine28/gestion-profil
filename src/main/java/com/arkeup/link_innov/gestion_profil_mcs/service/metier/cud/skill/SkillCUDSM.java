package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.skill;

import java.util.Optional;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Skill;

public interface SkillCUDSM {

	public Skill create(Skill skill);
	public void update(Skill skill);
	public Optional<Skill> delete(String id);
	public void addRecommendation(Skill skill, String username);
	public void deleteRecommendation(Skill skill, String username); 
}
