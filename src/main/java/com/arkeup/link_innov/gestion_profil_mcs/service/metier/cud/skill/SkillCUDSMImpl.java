package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.skill;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Skill;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.SkillRepository;

@Service
public class SkillCUDSMImpl implements SkillCUDSM {

	@Autowired
	SkillRepository skillRepository;

	@Override
	public Skill create(Skill skill) {
		skill.setCreateDate(new Date());
		skillRepository.save(skill);
		return skill;
	}

	@Override
	public void update(Skill skill) {
		skill.setModifDate(new Date());
		skillRepository.save(skill);
	}

	@Override
	public Optional<Skill> delete(String id) {
		Optional<Skill> entity = skillRepository.findById(id);
		if (entity.isPresent()) {
			skillRepository.delete(entity.get());
		}
		return entity;
	}

	@Override
	public void addRecommendation(Skill skill, String username) {
		List<String> recommendations = skill.getRecommended();

		if (! recommendations.contains(username)) {
			recommendations.add(username);
			skill.setRecommendedCount(skill.getRecommendedCount() + 1);
		}

		update(skill);
	}

	@Override
	public void deleteRecommendation(Skill skill, String username) {
		List<String> recommendations = skill.getRecommended();

		if (recommendations.contains(username)) {
			recommendations.remove(username);
			skill.setRecommendedCount(skill.getRecommendedCount() - 1);
		}

		update(skill);
	}

}

