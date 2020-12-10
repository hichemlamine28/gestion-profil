package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.GroupConsultation;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.GroupConsultationESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.GroupConsultationMongoRepository;

import java.util.List;

/**
 *
 * @author André
 *
 */
public interface GroupConsultationRepository
		extends CommonMongoToESRepository<GroupConsultation, String, GroupConsultationMongoRepository, GroupConsultationESRepository> {
	List<GroupConsultation> findByGroupIdAndUserGroupId(String groupId, String userGroupId);
}
