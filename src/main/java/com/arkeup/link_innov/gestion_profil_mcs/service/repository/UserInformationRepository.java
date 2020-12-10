package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserInformation;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.UserInformationESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.UserInformationMongoRepository;


public interface UserInformationRepository extends CommonMongoToESRepository<UserInformation, String, UserInformationMongoRepository, UserInformationESRepository> {
	UserInformation getInformation(String username);
}
