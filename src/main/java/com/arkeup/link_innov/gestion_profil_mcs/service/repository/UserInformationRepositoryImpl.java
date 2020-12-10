package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserInformation;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepositoryImpl;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.UserInformationESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.UserInformationMongoRepository;

@Repository
public class UserInformationRepositoryImpl extends CommonMongoToESRepositoryImpl<UserInformation,String,UserInformationMongoRepository,UserInformationESRepository> implements UserInformationRepository {

	@Override
	public UserInformation getInformation(String username) {
		return this.mongoRepository.findByUsername(username);
	}
}
