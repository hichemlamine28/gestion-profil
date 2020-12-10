package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.GroupConsultation;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;

import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.GroupConsultationESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.GroupConsultationMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *
 * @author André
 *
 */
@Repository
public class GroupConsultationRepositoryImpl
		extends CommonMongoToESRepositoryImpl<GroupConsultation, String, GroupConsultationMongoRepository, GroupConsultationESRepository>
		implements GroupConsultationRepository {

	@Override
	public List<GroupConsultation> findByGroupIdAndUserGroupId(String groupId, String userGroupId) {
		return mongoRepository.findByGroupIdAndUserGroupId(groupId, userGroupId);
	}
}
