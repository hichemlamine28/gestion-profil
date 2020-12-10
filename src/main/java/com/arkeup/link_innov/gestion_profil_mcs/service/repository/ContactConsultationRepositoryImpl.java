package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.ContactConsultation;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.ContactConsultationESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.ContactConsultationMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *
 * @author André
 *
 */
@Repository
public class ContactConsultationRepositoryImpl
		extends CommonMongoToESRepositoryImpl<ContactConsultation, String, ContactConsultationMongoRepository, ContactConsultationESRepository>
		implements ContactConsultationRepository {

	@Override
	public List<ContactConsultation> findByUserId(String userId) {
		return mongoRepository.findByUserId(userId);
	}
}
