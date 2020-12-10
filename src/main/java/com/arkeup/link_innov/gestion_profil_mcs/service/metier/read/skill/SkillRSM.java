package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.skill;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Skill;

public interface SkillRSM {

	public Optional<Skill> getSkill(String id);

	public Page<Skill> listSkill(String userId, Pageable pageable);

	public Page<Skill> findSkill(String name, Pageable pageable);

	// TODO
	public List<Skill> findByLabel(String label);

}
