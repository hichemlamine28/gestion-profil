package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.ContactConsultation;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.ContactConsultationESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.ContactConsultationMongoRepository;

import java.util.List;

/**
 *
 * @author André
 *
 */
public interface ContactConsultationRepository
		extends CommonMongoToESRepository<ContactConsultation, String, ContactConsultationMongoRepository, ContactConsultationESRepository> {
	List<ContactConsultation> findByUserId(String userId);
}
