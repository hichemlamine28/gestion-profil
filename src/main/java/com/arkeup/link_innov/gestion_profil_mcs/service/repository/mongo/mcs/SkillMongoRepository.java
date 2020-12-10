package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Skill;

public interface SkillMongoRepository extends MongoRepository<Skill,String> {
	@Query("{name: { $regex: ?0 } })")
	Page<Skill> findByNameContaining(String name, Pageable pageable);
	
	Page<Skill> findByUserId(String userId, Pageable pageable);
}
