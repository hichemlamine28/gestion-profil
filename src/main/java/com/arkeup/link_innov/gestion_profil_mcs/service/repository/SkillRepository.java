package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Skill;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.SkillESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.SkillMongoRepository;

public interface SkillRepository
		extends CommonMongoToESRepository<Skill, String, SkillMongoRepository, SkillESRepository> {
	Optional<Skill> getSkill(String id);

	Page<Skill> listSkill(String userId, Pageable pageable);

	Page<Skill> findSkill(String name, Pageable pageable);

	// TODO
	List<Skill> findByName(String label);
}
