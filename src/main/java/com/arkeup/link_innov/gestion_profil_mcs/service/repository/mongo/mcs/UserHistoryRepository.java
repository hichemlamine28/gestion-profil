package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserHistory;

@Repository
public interface UserHistoryRepository extends MongoRepository<UserHistory, String> {
//	public UserHistory findByFirstName(String firstName);
//
//	public List<UserHistory> findByAge(int age);
}