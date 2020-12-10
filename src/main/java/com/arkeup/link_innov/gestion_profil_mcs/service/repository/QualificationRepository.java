package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Qualification;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.QualificationESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.QualificationMongoRepository;

public interface QualificationRepository extends CommonMongoToESRepository<Qualification, String, QualificationMongoRepository, QualificationESRepository> {

	Optional<Qualification> getQualification(String id);
	Page<Qualification> listQualification(String userId, Pageable pageable);
	
}
