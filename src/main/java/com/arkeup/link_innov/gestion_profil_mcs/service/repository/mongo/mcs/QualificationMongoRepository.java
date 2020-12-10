package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Qualification;

public interface QualificationMongoRepository extends MongoRepository<Qualification,String> {
	Optional<Qualification> findById(String id);
	Page <Qualification> findByUserId(String userId, Pageable pageable);
}
