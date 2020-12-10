package com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserHistory;

@Repository
public interface UserHistoryMongoRepository extends MongoRepository<UserHistory, String> {
	
	@Query("{actionDate: { $regex: ?0 } })")
	List<UserHistory> findByactionDate(String actionDate);
//	public UserHistory findByFirstName(String firstName);
//
//	public List<UserHistory> findByAge(int age);
}