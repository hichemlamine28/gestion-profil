package com.arkeup.link_innov.gestion_profil_mcs.service.repository;

import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserHistory;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.common.mongo_es.CommonMongoToESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search.UserHistoryESRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.UserHistoryMongoRepository;

@Repository
public interface UserHistoryRepository
		extends CommonMongoToESRepository<UserHistory, String, UserHistoryMongoRepository, UserHistoryESRepository> {
//	public UserHistory findByFirstName(String firstName);
//
//	public List<UserHistory> findByAge(int age);
}