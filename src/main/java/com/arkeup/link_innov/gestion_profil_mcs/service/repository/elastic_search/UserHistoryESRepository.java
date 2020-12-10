package com.arkeup.link_innov.gestion_profil_mcs.service.repository.elastic_search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserHistory;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserInformation;

@Repository
public interface UserHistoryESRepository extends ElasticsearchRepository<UserHistory, String> {
//	public UserHistory findByFirstName(String firstName);
//
//	public List<UserHistory> findByAge(int age);
}