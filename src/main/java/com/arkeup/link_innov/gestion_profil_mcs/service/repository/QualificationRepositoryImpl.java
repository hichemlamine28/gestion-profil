package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Qualification;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.QualificationESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.QualificationMongoRepository;

@Repository
public class QualificationRepositoryImpl extends CommonMongoToESRepositoryImpl<Qualification, String, QualificationMongoRepository, QualificationESRepository> implements QualificationRepository {

	@Override
	public Optional<Qualification> getQualification(String id) {
		return this.mongoRepository.findById(id);
	}
	
	@Override
	public Page<Qualification> listQualification(String userId, Pageable pageable) {
		return this.mongoRepository.findByUserId(userId, pageable);
	}

}
