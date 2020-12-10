package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.skill;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Skill;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.SkillRepository;

@Service
public class SkillRSMImpl implements SkillRSM {

	@Autowired
	SkillRepository skillRepository;

	@Override
	public Optional<Skill> getSkill(String id) {
		return skillRepository.getSkill(id);
	}

	@Override
	public Page<Skill> listSkill(String userId, Pageable pageable) {
		return skillRepository.listSkill(userId, pageable);
	}

	@Override
	public Page<Skill> findSkill(String name, Pageable pageable) {
		return skillRepository.findSkill(name, pageable);
	}

	@Override
	public List<Skill> findByLabel(String label) {
		return skillRepository.findByName(label);
	}
}
